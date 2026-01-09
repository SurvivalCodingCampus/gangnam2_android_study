package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
//    private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

    fun bind(recipe: Recipe) {
        title.text = recipe.name
        chef.text = "By ${recipe.chef}"
        time.text = recipe.time
        rating.text = recipe.rating.toString()

        // 이미지 로딩 (예: Coil)
//        thumbnail.load(recipe.imageUrl)
    }
}
