package com.example.foodipy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CategoryItems")
data class CategoryItems (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @Expose
    @ColumnInfo(name = "idCategory")
    val idCategory: String,

    @Expose
    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @Expose
    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String,

    @Expose
    @ColumnInfo(name = "strCategoryDescription")
    val strCategoryDescription: String
)