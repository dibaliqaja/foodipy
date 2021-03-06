package com.example.foodipy.entities.converter

import androidx.room.TypeConverter
import com.example.foodipy.entities.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListCategoryConverter {
    @TypeConverter
    fun fromCategoryList(category: List<CategoryItems>): String?{
        return run {
            val gson = Gson()
            val type = object : TypeToken<CategoryItems>(){

            }.type
            gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString: String): List<CategoryItems>?{
        return run {
            val gson = Gson()
            val type = object :TypeToken<CategoryItems>(){

            }.type
            gson.fromJson(categoryString,type)
        }
    }
}