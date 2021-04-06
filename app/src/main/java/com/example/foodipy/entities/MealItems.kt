package com.example.foodipy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MealItems")
data class MealItems (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @Expose
    @ColumnInfo(name = "idMeal")
    val idMeal: String,

    @Expose
    @ColumnInfo(name = "categoryName")
    val categoryName: String,

    @Expose
    @ColumnInfo(name = "strMeal")
    val strMeal: String,

    @Expose
    @ColumnInfo(name = "strMealThumb")
    val strMealThumb: String
)