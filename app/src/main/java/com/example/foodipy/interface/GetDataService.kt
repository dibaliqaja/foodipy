package com.example.foodipy.`interface`

import com.example.foodipy.entities.Category
import com.example.foodipy.entities.Meal
import com.example.foodipy.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getListCategory(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getDetailMeal(@Query("i") id: String): Call<MealResponse>
}