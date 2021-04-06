package com.example.foodipy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodipy.R
import com.example.foodipy.entities.CategoryItems
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.RecipeViewHolder>() {

    private var listener: OnItemClickListener? = null
    private var context: Context? = null
    private var listCategory = ArrayList<CategoryItems>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_category.text = listCategory[position].strCategory

        Glide.with(context!!)
                .load(listCategory[position].strCategoryThumb)
                .into(holder.itemView.iv_category)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(listCategory[position].strCategory)
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    fun setData(list: List<CategoryItems>) {
        listCategory = list as ArrayList<CategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    interface OnItemClickListener{
        fun onClicked(categoryName: String)
    }
}