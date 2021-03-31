package com.example.foodipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodipy.adapter.CategoryAdapter
import com.example.foodipy.adapter.MealAdapter
import com.example.foodipy.entities.Recipes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var arrayListCategory = ArrayList<Recipes>()
    private var arrayListMeal = ArrayList<Recipes>()

    private var categoryAdapter = CategoryAdapter()
    private var mealAdapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Temporary Data
        arrayListCategory.add(Recipes(1, "Beef"))
        arrayListCategory.add(Recipes(2, "Chicken"))
        arrayListCategory.add(Recipes(3, "Desert"))
        arrayListCategory.add(Recipes(4, "Lamb"))

        categoryAdapter.setData(arrayListCategory)

        // Temporary Data
        arrayListMeal.add(Recipes(1, "Beef Burger"))
        arrayListMeal.add(Recipes(2, "Chicken Noodle"))
        arrayListMeal.add(Recipes(3, "Desert Brown Cake"))
        arrayListMeal.add(Recipes(4, "Lamb Hotpot"))

        mealAdapter.setData(arrayListMeal)

        rv_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_category.adapter = categoryAdapter

        rv_meal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_meal.adapter = mealAdapter
    }
}