package com.survivalcoding.legacy.p01_async.step01_java_thread_handler

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

    fun onAction() {
        listener?.onPhoneStateChanged()
    }

    override fun onDestroy() {
        listener = null
        super.onDestroy()
    }
}