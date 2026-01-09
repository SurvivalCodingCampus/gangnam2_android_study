package com.survivalcoding.gangnam2kiandroidstudy.domain.usercase

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.first

/**
 * 저장된 모든 레시피 목록을 가져오고, 각 레시피가 북마크되었는지 여부를 설정하는 유즈케이스입니다.
 * 유즈케이스는 애플리케이션의 비즈니스 로직을 캡슐화합니다.
 *
 * @property recipeRepository 레시피 데이터에 접근하기 위한 저장소입니다.
 * @property bookmarkRepository 북마크 데이터에 접근하기 위한 저장소입니다.
 */
class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    /**
     * 유즈케이스를 실행하는 함수입니다. `operator fun invoke`를 사용하면 클래스 인스턴스를 함수처럼 호출할 수 있습니다.
     * 예: `getSavedRecipesUseCase()`
     *
     * @return 북마크 상태가 업데이트된 레시피 목록
     */
    suspend operator fun invoke(): List<Recipe> {
        // 1. 레시피 저장소에서 모든 레시피를 가져옵니다.
        val allRecipes = recipeRepository.getRecipes()

        // 2. 북마크 저장소에서 북마크된 레시피 ID 목록을 한 번만 가져옵니다.
        val bookmarkedRecipeIds = bookmarkRepository.getBookmarkedRecipeIds().first().toSet()

        // 3. 전체 레시피 목록에서 북마크된 것만 필터링합니다.
        return allRecipes
            .filter { recipe -> bookmarkedRecipeIds.contains(recipe.id) }
            .map { recipe ->
                // 4. 북마크 상태를 true로 설정하여 반환합니다.
                recipe.copy(isBookmarked = true)
            }
    }
}
