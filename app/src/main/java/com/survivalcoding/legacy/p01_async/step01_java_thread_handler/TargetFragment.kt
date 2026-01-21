package com.survivalcoding.legacy.p01_async.step01_java_thread_handler

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.survivalcoding.gangnam2kiandroidstudy.R

class TargetFragment : Fragment(R.layout.fragment_target) {
    interface PhoneStateListener {
        fun onPhoneStateChanged()
    }

    var listener: PhoneStateListener? = null

    fun setOnPhoneStateListener(listener: PhoneStateListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 복원
    }

    fun onAction() {
        listener?.onPhoneStateChanged()
    }

    override fun onDestroy() {
        listener = null
        super.onDestroy()
    }

    // 저장
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}