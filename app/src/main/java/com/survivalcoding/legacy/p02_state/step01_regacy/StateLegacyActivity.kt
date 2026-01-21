package com.survivalcoding.legacy.p02_state.step01_regacy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ActivityStateLegacyBinding
import kotlin.getValue

class StateLegacyActivity : AppCompatActivity() {
    var count = 0

    val binding by lazy {
        ActivityStateLegacyBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel: StateLegacyViewModel by viewModels()

        // 복원 찬스
        if (savedInstanceState != null) {
//            count = savedInstanceState.getInt("count")
//            binding.countTextView.text = count.toString()

            binding.countTextView.text = viewModel.count.toString()
        }

        binding.plusButton.setOnClickListener {
//            count++
//            binding.countTextView.text = count.toString()

            viewModel.increment()
            binding.countTextView.text = viewModel.count.toString()
        }
    }

    // 화면 돌 때만 호출
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // 복원 찬스
    }
}