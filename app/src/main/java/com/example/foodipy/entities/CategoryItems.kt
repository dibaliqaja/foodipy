package com.example.foodipy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryItems")
data class CategoryItems (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "idCategory")
    val idCategory: String,

    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String,

    @ColumnInfo(name = "strCategoryDescription")
    val strCategoryDescription: String
)