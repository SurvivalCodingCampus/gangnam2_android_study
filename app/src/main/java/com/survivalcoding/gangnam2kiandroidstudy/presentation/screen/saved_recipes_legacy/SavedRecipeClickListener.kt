package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes_legacy

/**
 * SavedRecipeClickListener
 *
 * Adapter는 "무엇이 클릭되었는지"만 알리고,
 * 실제로 무엇을 할지는 이 인터페이스를 구현한 쪽(Fragment)이 결정한다.
 *
 * - Adapter는 UI 로직을 몰라야 하고
 * - Fragment가 화면 전환 / 토스트 / 네비게이션 등을 담당한다
 */
interface SavedRecipeClickListener {

    /**
     * RecyclerView 아이템이 클릭되는 순간 호출된다.
     *
     * @param recipeTitle 클릭된 아이템의 데이터
     */
    fun onRecipeClick(recipeTitle: String)
}
