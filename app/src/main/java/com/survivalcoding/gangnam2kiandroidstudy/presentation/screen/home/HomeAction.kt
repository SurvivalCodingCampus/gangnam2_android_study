package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface HomeAction {

    // 상단 검색 아이콘 클릭
    object ClickSearch : HomeAction

    // 하단 북마크(저장된 레시피) 클릭
    object ClickBookmark : HomeAction

    // 필터 버튼 클릭
    object ClickFilter : HomeAction

    // 카테고리 선택
    data class ClickCategory(
        val category: String
    ) : HomeAction

    // 홈 진입 시 데이터 로딩
    object LoadHome : HomeAction
}
