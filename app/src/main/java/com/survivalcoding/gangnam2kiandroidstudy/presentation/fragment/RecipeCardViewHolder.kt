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

    private val title: TextView = itemView.findViewById(R.id.title)

    fun bind(recipe: Recipe) {
        title.text = recipe.name
    }
}
