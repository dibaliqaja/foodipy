package com.example.foodipy.entities

import androidx.room.*
import com.example.foodipy.entities.converter.ListCategoryConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Categories")
data class Categories(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "listCategoryItems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(ListCategoryConverter::class)
    var listCategoryItems: List<CategoryItems>? = null
)