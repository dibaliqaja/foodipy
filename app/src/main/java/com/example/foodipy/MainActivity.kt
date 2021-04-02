package com.example.foodipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodipy.adapter.CategoryAdapter
import com.example.foodipy.adapter.MealAdapter
import com.example.foodipy.database.RecipeDatabase
import com.example.foodipy.entities.CategoryItems
import com.example.foodipy.entities.Recipes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private var arrayListCategory = ArrayList<CategoryItems>()
    private var arrayListMeal = ArrayList<Recipes>()

    private var categoryAdapter = CategoryAdapter()
    private var mealAdapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        // Temporary Data
        arrayListMeal.add(Recipes(1, "Beef Burger"))
        arrayListMeal.add(Recipes(2, "Chicken Noodle"))
        arrayListMeal.add(Recipes(3, "Desert Brown Cake"))
        arrayListMeal.add(Recipes(4, "Lamb Hotpot"))

        mealAdapter.setData(arrayListMeal)

        rv_meal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_meal.adapter = mealAdapter
    }

    private fun getData() {
        launch {
            this.let {
                val category = RecipeDatabase.getDatabase(this@MainActivity).recipeDao().getAllCategory()
                arrayListCategory = category as ArrayList<CategoryItems>
                arrayListCategory.reverse()
                categoryAdapter.setData(arrayListCategory)

                rv_category.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_category.adapter = categoryAdapter
            }
        }
    }
}