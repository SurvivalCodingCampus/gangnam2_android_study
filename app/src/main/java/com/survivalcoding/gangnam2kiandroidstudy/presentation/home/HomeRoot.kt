package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * 이 파일은 앱의 '홈' 화면을 구성하는 최상위 UI 컴포넌트입니다.
 * Jetpack Compose를 사용하여 UI를 선언적으로 빌드합니다.
 */

/**
 * HomeRoot는 홈 화면의 메인 Composable 함수입니다.
 * 이 함수는 화면에 표시될 내용을 정의하고, 사용자 상호작용을 처리하기 위해
 * ViewModel과 연결하는 역할을 합니다.
 *
 * @param modifier UI 요소의 외부 모양이나 동작을 수정하는 데 사용됩니다. (예: 크기, 패딩, 배경색)
 * @param viewModel HomeViewModel의 인스턴스입니다. koinViewModel()을 통해 Koin으로부터 주입받습니다.
 *                  이 ViewModel은 화면의 상태(데이터)를 관리하고 비즈니스 로직을 처리합니다.
 */
@Composable
fun HomeRoot(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    // viewModel.uiState는 ViewModel이 가지고 있는 화면의 상태(State) 정보입니다.
    // collectAsStateWithLifecycle() 함수는 StateFlow<HomeState>를 Compose가 인식할 수 있는 State<HomeState>로 변환합니다.
    // 이렇게 하면 uiState가 변경될 때마다 Composable 함수가 자동으로 다시 그려집니다(리컴포지션).
    // 'by' 키워드를 사용하면 .value 없이 state 변수를 직접 HomeState 타입처럼 사용할 수 있습니다.
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // ViewModel에서 발생하는 이벤트를 감지하고 처리합니다.
    // LaunchedEffect는 HomeRoot가 화면에 처음 표시될 때 한 번 실행됩니다.
    // 그 후 eventFlow를 계속 구독하며 새로운 이벤트가 올 때마다 반응합니다.
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                // ViewModel로부터 NavigateToSearch 이벤트가 오면,
                // 파라미터로 받은 onSearchClick 콜백 함수를 실행합니다.
                HomeEvent.NavigateToSearch -> onSearchClick()
            }
        }
    }

    // HomeScreen Composable을 호출하여 실제 UI를 그립니다.
    // 화면을 구성하는 데 필요한 모든 데이터(state)와 사용자 이벤트 핸들러(람다 함수)를 전달합니다.
    HomeScreen(
        state = state, // 화면에 표시할 데이터 (레시피 목록, 카테고리 등)
        onAction = viewModel::onAction, // 사용자 이벤트 핸들러 (카테고리 클릭, 검색어 변경 등)
    )
}

/**
 * Android Studio의 Preview 기능을 위한 Composable 함수입니다.
 * @Preview 어노테이션을 붙이면 앱을 실제로 빌드하고 실행하지 않아도
 * Android Studio 편집기 내에서 UI가 어떻게 보일지 미리 확인할 수 있습니다.
 */
@Preview(showBackground = true) // showBackground = true는 미리보기 화면에 배경을 표시해줍니다.
@Composable
fun HomeRootPreview() {

    // 미리보기에서는 실제 ViewModel을 사용할 수 없으므로,
    // UI가 어떻게 보일지 확인하기 위해 가짜(dummy) 데이터(HomeState())와
    // 비어있는 동작({ })을 HomeScreen에 전달합니다.
    HomeScreen(
        state = HomeState(), // 비어있는 기본 상태로 미리보기를 생성합니다.
        onAction = { }, // 비어있는 동작을 전달합니다.
    )
}
