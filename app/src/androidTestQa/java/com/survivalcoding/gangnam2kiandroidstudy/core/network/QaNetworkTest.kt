package com.survivalcoding.gangnam2kiandroidstudy.core.network

import android.content.Context
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.MainActivity
import com.survivalcoding.gangnam2kiandroidstudy.core.di.DebugNetworkEntryPoint
import dagger.hilt.android.EntryPointAccessors
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QaNetworkTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun networkDisconnected_startButtonDisabled() {
        // GIVEN
        val context = ApplicationProvider.getApplicationContext<Context>()
        val mockNetwork =
            EntryPointAccessors.fromApplication(
                context,
                DebugNetworkEntryPoint::class.java
            ).mockNetworkMonitor()

        // WHEN
        composeRule.runOnIdle {
            mockNetwork.setDisconnected()
        }

        // THEN
        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Start Cooking")
            .assertIsNotEnabled()
    }

    @Test
    fun networkConnected_startButtonEnabled() {
        // GIVEN
        val context = ApplicationProvider.getApplicationContext<Context>()
        val mockNetwork =
            EntryPointAccessors.fromApplication(
                context,
                DebugNetworkEntryPoint::class.java
            ).mockNetworkMonitor()

        // WHEN
        composeRule.runOnIdle {
            mockNetwork.setConnected()
        }

        // THEN
        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Start Cooking")
            .assertIsEnabled()
    }
}
