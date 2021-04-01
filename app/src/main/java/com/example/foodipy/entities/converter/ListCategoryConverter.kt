package com.example.foodipy.entities.converter

import androidx.room.TypeConverter
import com.example.foodipy.entities.Categories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListCategoryConverter {
    @TypeConverter
    fun fromListCategory(categories: List<Categories>): String? {
        return run {
            val gson = Gson()
            val type = object : TypeToken<Categories>() {

            }.type
            gson.toJson(categories,type)
        }
    }

    @TypeConverter
    fun toListCategory(categories: String): List<Categories>? {
        return run {
            val gson = Gson()
            val type = object : TypeToken<Categories>() {

            }.type
            gson.fromJson(categories,type)
        }
    }
}