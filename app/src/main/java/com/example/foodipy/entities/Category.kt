package com.example.foodipy.entities

import androidx.room.*
import com.example.foodipy.entities.converter.ListCategoryConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(ListCategoryConverter::class)
    var listCategoryItems: List<CategoryItems>? = null
)