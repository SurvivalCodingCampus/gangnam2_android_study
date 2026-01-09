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
    private val onBookmarkClick: (Int) -> Unit,
    private val onCardClick: (Int) -> Unit
) : ListAdapter<Recipe, SavedRecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding, onBookmarkClick, onCardClick)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecipeViewHolder(
        private val binding: ItemRecipeCardBinding,
        private val onBookmarkClick: (Int) -> Unit,
        private val onCardClick: (Int) -> Unit
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
                    onCardClick(recipe.id)
                }

                btnBookmark.setOnClickListener {
                    onBookmarkClick(recipe.id)
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