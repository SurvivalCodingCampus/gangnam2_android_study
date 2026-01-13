package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.legacy

import androidx.recyclerview.widget.DiffUtil
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe

class DiffUtilCallback(
    private val oldList: List<Recipe>,
    private val newList: List<Recipe>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame( //old와 new가 같은 객체인가? (hashCode)
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id


    }

    override fun areContentsTheSame( //old와 new가 같은 데이터를 갖고있는가?
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}