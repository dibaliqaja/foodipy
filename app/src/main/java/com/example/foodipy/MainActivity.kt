package com.example.foodipy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodipy.adapter.CategoryAdapter
import com.example.foodipy.adapter.MealAdapter
import com.example.foodipy.database.RecipeDatabase
import com.example.foodipy.entities.CategoryItems
import com.example.foodipy.entities.MealItems
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private var arrayListCategory = ArrayList<CategoryItems>()
    private var arrayListMeal = ArrayList<MealItems>()
    private var categoryAdapter = CategoryAdapter()
    private var mealAdapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCategoryData()

        categoryAdapter.setClickListener(onClicked)
//        mealAdapter.setClickListener(onClickedCategory)
    }

    private val onClicked = object : CategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealData(categoryName)
        }
    }

//    private val onClickedCategory  = object : CategoryAdapter.OnItemClickListener{
//        override fun onClicked(id: String) {
//            val intent = Intent(this@MainActivity, DetailActivity::class.java)
//            intent.putExtra("id", id)
//            startActivity(intent)
//        }
//    }

    private fun getCategoryData() {
        launch {
            this.let {
                val category = RecipeDatabase.getDatabase(this@MainActivity).recipeDao().getAllCategory()
                arrayListCategory = category as ArrayList<CategoryItems>
                arrayListCategory.reverse()

                getMealData(arrayListCategory[0].strCategory)
                categoryAdapter.setData(arrayListCategory)

                rv_category.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_category.adapter = categoryAdapter
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getMealData(categoryName: String) {
        tv_meal_list.text = "$categoryName List"
        launch {
            this.let {
                val meal = RecipeDatabase.getDatabase(this@MainActivity).recipeDao().getSpecificMealList(categoryName)
                arrayListMeal = meal as ArrayList<MealItems>
                mealAdapter.setData(arrayListMeal)

                rv_meal.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_meal.adapter = mealAdapter
            }
        }
    }
}