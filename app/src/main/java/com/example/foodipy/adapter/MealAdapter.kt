package com.example.foodipy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodipy.R
import com.example.foodipy.entities.Recipes
import kotlinx.android.synthetic.main.item_meal.view.*

class MealAdapter: RecyclerView.Adapter<MealAdapter.RecipeViewHolder>() {

    private var listMeal = ArrayList<Recipes>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun setData(list: List<Recipes>) {
        listMeal = list as ArrayList<Recipes>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_meal.text = listMeal[position].mealName
    }

    override fun getItemCount(): Int {
        return listMeal.size
    }
}