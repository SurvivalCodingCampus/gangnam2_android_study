package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.RecipeCardBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe


// ViewHolder: 아이템 1개의 UI 담당
// * 데이터 바인딩
// * 클릭 이벤트 전달
// * UI 갱신만 담당
class RecipeCardViewHolder(
    private val binding: RecipeCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(recipe: Recipe, onClick: (Recipe) -> Unit) {
        binding.tvTitle.text = recipe.name
        binding.tvChef.text = "By ${recipe.chef}"
        binding.tvTime.text = recipe.time
        binding.tvRating.text = recipe.rating.toString()

        // 이미지 로딩
        binding.ivRecipe.load(recipe.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
        }

        binding.root.setOnClickListener {
            onClick(recipe)
        }
    }

    fun clear() {
        binding.ivRecipe.setImageDrawable(null)
    }
}
