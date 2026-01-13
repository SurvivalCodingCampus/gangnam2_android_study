package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

class RecipeRecyclerAdapter(
    private val listener: SavedRecipesActionListener,
) : RecyclerView.Adapter<RecipeViewHolder>() {

    private var items = listOf<Recipe>()

    /*
    DiffUtil 사용해서 변경된 데이터만 업데이트
     */
    fun updateData(newItems: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(this.items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    /*
    saved_recipe_card 레이아웃을 ViewHolder 에 주입 후 생성
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    /*
    RecipeCard 데이터 설정
     */
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = items[position]
        holder.bind(recipe, listener)
    }

    /*
    아이템 개수
     */
    override fun getItemCount(): Int {
        return items.size
    }

}