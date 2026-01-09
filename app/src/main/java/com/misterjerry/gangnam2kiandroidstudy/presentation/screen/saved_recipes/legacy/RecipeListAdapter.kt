package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes.legacy

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.misterjerry.gangnam2kiandroidstudy.R
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe

class RecipeListAdapter(
    private val recipeList: List<Recipe>
) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rating: TextView = view.findViewById(R.id.tv_rating)
        val recipeName: TextView = view.findViewById(R.id.tv_recipe_name)
        val chefName: TextView = view.findViewById(R.id.tv_chef_name)
        val time: TextView = view.findViewById(R.id.tv_time)
        val bookMarkIcon: ImageView = view.findViewById(R.id.btn_bookmark)
        val card: ImageView = view.findViewById(R.id.iv_background)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_saved_recipe, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Log.d("onBindViewHolder", "onBindViewHolder: $position")
        holder.rating.text = recipeList[position].rating.toString()
        holder.recipeName.text = recipeList[position].name
        holder.chefName.text = "By ${recipeList[position].chef}"
        holder.time.text = "${recipeList[position].time} min"
        if (recipeList[position].isSaved) {
            holder.bookMarkIcon.imageTintList = ColorStateList.valueOf(Color.parseColor("#71B1A1"))
        } else {
            holder.bookMarkIcon.imageTintList = ColorStateList.valueOf(Color.parseColor("#A9A9A9"))
        }
        holder.card.load(recipeList[position].image)
    }

    override fun getItemCount(): Int = recipeList.size


}