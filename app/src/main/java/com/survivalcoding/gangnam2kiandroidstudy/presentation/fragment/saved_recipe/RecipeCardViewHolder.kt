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
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe


// ViewHolder: 아이템 1개의 UI 담당
// * 데이터 바인딩
// * 클릭 이벤트 전달
// * UI 갱신만 담당
class RecipeCardViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.tvTitle)
    private val chef: TextView = itemView.findViewById(R.id.tvChef)
    private val time: TextView = itemView.findViewById(R.id.tvTime)
    private val rating: TextView = itemView.findViewById(R.id.tvRating)
    private val thumbnail: ImageView = itemView.findViewById(R.id.ivRecipe)

    @SuppressLint("SetTextI18n")
    fun bind(recipe: Recipe, onClick: (Recipe) -> Unit) {
        title.text = recipe.name
        chef.text = "By ${recipe.chef}"
        time.text = recipe.time
        rating.text = recipe.rating.toString()

        // 이미지 로딩
        thumbnail.load(recipe.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
        }

        itemView.setOnClickListener {
            onClick(recipe)
        }
    }

    fun clear() {
        thumbnail.setImageDrawable(null)
    }
}
