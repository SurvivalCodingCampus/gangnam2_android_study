package com.survivalcoding.legacy.p03_navigation_component.step01_regacy

import android.R.attr.label
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ActivityNavigationLegacyBinding
import com.survivalcoding.legacy.p01_async.step01_java_thread_handler.TargetFragment
import kotlinx.serialization.Serializable

class NavigationLegacyActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityNavigationLegacyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController

        navController.graph = navController.createGraph(
            startDestination = Route.StartScreen
        ) {
            fragment<NavigationStartFragment, Route.StartScreen> {
                label = "시작"
            }
            fragment<NavigationTargetFragment, Route.TargetScreen> {
                label = "목표"
            }
        }

    }
}

sealed interface Route {
    @Serializable
    data object StartScreen

    @Serializable
    data class TargetScreen(val target: String)
}