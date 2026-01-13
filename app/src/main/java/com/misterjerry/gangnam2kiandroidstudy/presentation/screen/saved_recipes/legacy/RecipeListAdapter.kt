package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.legacy

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.misterjerry.gangnam2kiandroidstudy.R
import com.misterjerry.gangnam2kiandroidstudy.databinding.ItemSavedRecipeBinding
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe

class RecipeListAdapter(
    recipeList: List<Recipe>,
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(pos: Int)
        fun deleteItem(recipeId: Int)
    }

    private val itemList = recipeList.toMutableList()

    //클릭 리스너 정의
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemSavedRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.tvRating.text = recipe.rating.toString()
            binding.tvRecipeName.text = recipe.name
            binding.tvChefName.text = recipe.chef
            binding.tvTime.text = recipe.time
            binding.btnBookmark.apply {
                setImageResource(R.drawable.outline_bookmark_inactive)
                val color = "#71B1A1" //if (recipe.isSaved) "#71B1A1" else "#9E9E9E"
                imageTintList = ColorStateList.valueOf(color.toColorInt())
            }
            binding.ivBackground.load(
                data = recipe.image
            )
            binding.root.setOnClickListener {
                val pos = getAbsoluteAdapterPosition() // 현재 아이템의 위치
                if (pos != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClicked(pos)
                }
            }
            binding.btnBookmark.setOnClickListener {
                val pos = getAbsoluteAdapterPosition() // 현재 아이템의 위치
                if (pos != RecyclerView.NO_POSITION) {
                    onItemClickListener?.deleteItem(itemList[pos].id)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemSavedRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(newList: List<Recipe>) {
        val diffCallback = DiffUtilCallback(itemList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList.clear()
        itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}