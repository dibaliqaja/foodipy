package com.example.foodipy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodipy.dao.RecipeDao
import com.example.foodipy.entities.*
import com.example.foodipy.entities.converter.ListCategoryConverter
import com.example.foodipy.entities.converter.ListMealConverter

@Database(entities = [Recipes::class, Category::class, CategoryItems::class, Meal::class, MealItems::class], version = 1, exportSchema = false)
@TypeConverters(ListCategoryConverter::class, ListMealConverter::class)
abstract class RecipeDatabase: RoomDatabase() {
    companion object {
        private var recipeDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipeDatabase == null) {
                recipeDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipeDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}