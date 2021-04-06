package com.example.foodipy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.foodipy.entities.converter.ListMealConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Meal")
class Meal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "mealItems")
    @Expose
    @SerializedName("meals")
    @TypeConverters(ListMealConverter::class)
    var listMealItems: List<MealItems>? = null
)

