package com.example.foodipy.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodipy.R
import com.example.foodipy.entities.MealItems
import kotlinx.android.synthetic.main.item_meal.view.*

class MealAdapter: RecyclerView.Adapter<MealAdapter.RecipeViewHolder>() {

    private var listener: MealAdapter.OnItemClickListener? = null
    private var context: Context? = null
    private var listMeal = ArrayList<MealItems>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_meal.text = listMeal[position].strMeal

        Glide.with(context!!).load(listMeal[position].strMealThumb).into(holder.itemView.iv_meal)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(listMeal[position].idMeal)
        }
    }

    override fun getItemCount(): Int {
        return listMeal.size
    }

    fun setData(list: List<MealItems>) {
        listMeal = list as ArrayList<MealItems>
    }

    fun setClickListener(listener1: MealAdapter.OnItemClickListener) {
        listener = listener1
    }

    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}