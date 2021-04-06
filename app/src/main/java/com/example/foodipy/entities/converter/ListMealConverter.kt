package com.example.foodipy.entities.converter

import androidx.room.TypeConverter
import com.example.foodipy.entities.CategoryItems
import com.example.foodipy.entities.MealItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListMealConverter {
    @TypeConverter
    fun fromMealList(meal: List<MealItems>):String?{
        return run {
            val gson = Gson()
            val type = object : TypeToken<MealItems>(){

            }.type
            gson.toJson(meal,type)
        }
    }

    @TypeConverter
    fun toMealList(mealString: String): List<MealItems>?{
        return run {
            val gson = Gson()
            val type = object :TypeToken<MealItems>(){

            }.type
            gson.fromJson(mealString,type)
        }
    }
}