package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe

/**
 * 레시피 리스트 아이템에서 발생하는 사용자 클릭 이벤트를 처리하기 위한 인터페이스
 * Adapter와 Fragment/Activity 사이의 통신 역할을 담당
 */
interface OnRecipeClickListener {
    /** 레시피 아이템 자체를 클릭했을 때 (상세 이동 등) */
    fun onRecipeClick(recipeId: Int)

    /** 북마크(별) 버튼을 클릭했을 때 */
    fun onBookmarkClick(recipeId: Int)
}