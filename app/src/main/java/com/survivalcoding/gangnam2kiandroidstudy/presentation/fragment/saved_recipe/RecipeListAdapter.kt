package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.saved_recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

// Adapter: RecyclerView에 데이터를 공급하는 역할
// * 데이터 → ViewHolder 연결
// * LazyColumn의 items {} 블록에 해당
class RecipeListAdapter(
    private val onClick: (Recipe) -> Unit = {}
) : ListAdapter<Recipe, RecipeCardViewHolder>(RecipeDiffCallback) {

    // RecyclerView에서 새로운 아이템 View 생성할 때
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeCardViewHolder {
        val view = LayoutInflater.from(parent.context)      // XML → View 객체로 변환
            .inflate(R.layout.recipe_card, parent, false)
        return RecipeCardViewHolder(view)       // ViewHolder 생성
    }

    // ViewHolder가 화면에 나타날 때
    // 재사용될 때마다 반복 호출
    override fun onBindViewHolder(
        holder: RecipeCardViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), onClick)
    }

    // ViewHolder가 화면에서 사라질 때
    // 이미지 null로
    override fun onViewRecycled(holder: RecipeCardViewHolder) {
        holder.clear()
    }
}

