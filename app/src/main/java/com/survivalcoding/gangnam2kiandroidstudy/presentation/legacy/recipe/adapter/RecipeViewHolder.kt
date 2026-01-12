package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.adapter

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ItemRecipeBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.recipe.OnRecipeClickListener

/**
 * 주어진 데이터를 View에 바인딩
 * 데이터 판단이나 로직 없이 렌더링과 이벤트 전달만 담당
 */
class RecipeViewHolder(
    private val binding: ItemRecipeBinding,
    private val listener: OnRecipeClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: Recipe, isBookmarked: Boolean) = with(binding) {
        tvTitle.text = recipe.title
        tvChef.text = recipe.chefName
        tvTime.text = recipe.time
        tvRating.text = "${recipe.rating}"

        //이미지 로딩
        // ViewHolder는 “이 URL을 이 ImageView에 보여줘라”만 함
        binding.ivThumbnail.load(recipe.imageUrls) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }

        root.setOnClickListener {
            listener.onRecipeClick(recipe.id)
        }

        // bind 함수가 isBookmarked 상태를 받아 버튼의 isSelected 속성을 설정하고, 클릭 시 리스너를 호출
        btnBookmark.isSelected = isBookmarked
        btnBookmark.setOnClickListener {
            listener.onBookmarkClick(recipe.id)
        }
    }
}