package com.example.foodipy.`interface`

import com.example.foodipy.entities.Category
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("categories.php")
    fun getListCategory(): Call<Category>
}