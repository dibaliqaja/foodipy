package com.example.foodipy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "mealName")
    var mealName: String
): Serializable