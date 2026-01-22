package com.survivalcoding.legacy.p03_navigation_component.step01_regacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.survivalcoding.gangnam2kiandroidstudy.R

class NavigationStartFragment : Fragment(R.layout.fragment_navigation_start) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.text_view).setOnClickListener {
            findNavController().navigate(route = Route.TargetScreen("Hello"))
        }

    }
}