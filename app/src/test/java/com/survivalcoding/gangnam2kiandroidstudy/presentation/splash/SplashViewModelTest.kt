package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkStatusRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockNetworkRepository = mockk<NetworkStatusRepository>()

    private lateinit var viewModel: SplashViewModel

    @Test
    fun `온라인 테스트`() = runTest {
        coEvery { mockNetworkRepository.observeNetworkStatus() } returns flowOf(NetworkStatus.Connected)
        viewModel = SplashViewModel(mockNetworkRepository)

        advanceUntilIdle()

        assertEquals(true, viewModel.state.value.isConnected)
    }

    @Test
    fun `오프라인 테스트`() = runTest {
        coEvery { mockNetworkRepository.observeNetworkStatus() } returns flowOf(NetworkStatus.Disconnected)
        viewModel = SplashViewModel(mockNetworkRepository)

        advanceUntilIdle()

        assertEquals(false, viewModel.state.value.isConnected)
    }

}