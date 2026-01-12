package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ItemRecipeBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.OnRecipeClickListener

/**
 * Recipe 목록을 RecyclerView에 표시하기 위한 어댑터
 * ListAdapter를 상속받아 효율적인 리스트 업데이트(DiffUtil)를 지원
 */
class RecipeAdapter(
    private val listener: OnRecipeClickListener
) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffCallback) {

    // 현재 사용자가 북마크한 레시피의 ID 목록을 저장
    private var bookmarkedIds: Set<Int> = emptySet()

    /**
     * 외부(주로 Fragment나 Activity)에서 ViewModel의 북마크 데이터를 관찰하다가
     * 데이터가 변경되면 이 함수를 호출하여 어댑터에 북마크 상태를 동기화함
     */
    fun submitBookmarkedIds(ids: Set<Int>) {
        bookmarkedIds = ids
        // 북마크 상태가 바뀌면 전체 아이템을 다시 그려서 아이콘 상태를 업데이트함
        notifyDataSetChanged()
    }


    /**
     * RecyclerView가 새로운 아이템 뷰를 생성해야 할 때 호출됨
     * item_recipe.xml 레이아웃을 인플레이트하여 RecipeViewHolder를 생성함
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding, listener)
    }

    /**
     * 특정 위치(position)의 데이터를 뷰홀더에 바인딩할 때 호출됨
     * 레시피 데이터와 해당 레시피의 북마크 여부를 함께 전달함
     */
    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) {
        val recipe = getItem(position)
        // 레시피 ID가 bookmarkedIds에 포함되어 있는지 확인하여 true/false 전달
        holder.bind(recipe, bookmarkedIds.contains(recipe.id))
    }
}