package com.survivalcoding.legacy.p01_async.step01_java_thread_handler;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.survivalcoding.gangnam2kiandroidstudy.R;

// https://developer.android.com/guide/components/processes-and-threads?hl=ko
public class JavaThreadHandlerActivity extends AppCompatActivity {
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_thread_handler);

        Button button = findViewById(R.id.start_button);
        TextView resultTextView = findViewById(R.id.result_text_view);

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

                            resultTextView.post(new Runnable() {
                                @Override
                                public void run() {
                                    resultTextView.setText(String.valueOf(count));  // 안 터짐
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
}