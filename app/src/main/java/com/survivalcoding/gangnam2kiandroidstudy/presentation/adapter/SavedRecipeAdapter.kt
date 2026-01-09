package com.survivalcoding.gangnam2kiandroidstudy.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ItemRecipeCardBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

class SavedRecipeAdapter(
    private val listener: OnRecipeClickListener
) : ListAdapter<Recipe, SavedRecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    // 콜백 인터페이스
    interface OnRecipeClickListener {
        fun onRecipeClick(recipeId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecipeViewHolder(
        private val binding: ItemRecipeCardBinding,
        private val listener: OnRecipeClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {

                // 이미지 로드
                Glide.with(ivRecipeImage.context)
                    .load(recipe.imageUrls)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivRecipeImage)

                // 텍스트 설정
                tvRecipeTitle.text = recipe.title
                tvChefName.text = "By ${recipe.chef}"
                tvRating.text = recipe.rating.toString()
                tvCookingTime.text = recipe.time

                // 클릭 리스너
                cardView.setOnClickListener {
                    listener.onRecipeClick(recipe.id)
                }

            }
        }
    }

    private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}