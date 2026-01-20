package com.survivalcoding.legacy.p01_async.step01_java_thread_handler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.survivalcoding.gangnam2kiandroidstudy.R;

// https://developer.android.com/guide/components/processes-and-threads?hl=ko
public class JavaThreadHandlerActivity extends AppCompatActivity {
    private int count = 0;

    private Handler handler = new Handler(Looper.getMainLooper());

    // null
    Button button;
    TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_thread_handler);

        button = findViewById(R.id.start_button);
        resultTextView = findViewById(R.id.result_text_view);

        // Main Thread : UI 그리는 스레드
        Log.d("JavaThreadHandlerActivity", Thread.currentThread().getName());

        button.setOnClickListener(v -> {
            // Worker Thread : 백그라운드 스레드
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("JavaThreadHandlerActivity", Thread.currentThread().getName());

                    for (int i = 0; i < 3; i++) {
                        // 1초 딜레이
                        try {
                            Thread.sleep(1000);
                            count++;
                            Log.d("JavaThreadHandlerActivity", String.valueOf(count));

                            // 백그라운드 스레드에서 접근했기 때문에 터짐
//                            resultTextView.setText(String.valueOf(count));  // 터짐

                            // Activity 전용이라 Fragment 에서 못 씀
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    resultTextView.setText(String.valueOf(count));  // 안 터짐
//                                }
//                            });

                            // View.post
//                            resultTextView.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    resultTextView.setText(String.valueOf(count));  // 안 터짐
//                                }
//                            });

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    resultTextView.setText(String.valueOf(count));
                                }
                            });

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        });
    }


    // 쓰지마 -> https://developer.android.com/develop/background-work/background-tasks/asynchronous/java-threads?hl=ko
    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // 오래걸리는 작업 Background Thread
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // 다 끝나고 UI 그리는 작업 Main Thread
            resultTextView.setText(String.valueOf(count));
        }
    }
}