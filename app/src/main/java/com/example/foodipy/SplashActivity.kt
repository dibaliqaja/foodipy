package com.example.foodipy

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodipy.`interface`.GetDataService
import com.example.foodipy.database.RecipeDatabase
import com.example.foodipy.entities.Category
import com.example.foodipy.entities.Meal
import com.example.foodipy.entities.MealItems
import com.example.foodipy.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {

    private var READ_STORAGE_PERM = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        readStorageTask()
        btn_started.setOnClickListener {
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getCategories() {
        val service = RetrofitInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getListCategory()
        call.enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                for (arr in response.body()!!.listCategoryItems!!) {
                    getMeals(arr.strCategory)
                }

                insertCategoryData(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                pb_started.visibility = View.INVISIBLE

                Toast.makeText(this@SplashActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getMeals(categoryName: String) {
        val service = RetrofitInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object: Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                insertMealData(categoryName, response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                pb_started.visibility = View.INVISIBLE

                Toast.makeText(this@SplashActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun insertCategoryData(category: Category?) {
        launch {
            this.let {
                for (arr in category!!.listCategoryItems!!) {
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertCategory(arr)
                }
            }
        }
    }

    fun insertMealData(categoryName: String, meal: Meal?) {
        launch {
            this.let {
                for (arr in meal!!.listMealItems!!) {
                    val mealItemModel = MealItems(
                            arr.id,
                            arr.idMeal,
                            categoryName,
                            arr.strMeal,
                            arr.strMealThumb
                    )
                    RecipeDatabase.getDatabase(this@SplashActivity)
                            .recipeDao().insertMeal(mealItemModel)
                    Log.d("mealData", arr.toString())
                }
                btn_started.visibility = View.VISIBLE
            }
        }
    }

    private fun clearDatabase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
            }
        }
    }

    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun readStorageTask() {
        if (hasReadStoragePermission()) {
            clearDatabase()
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage",
                READ_STORAGE_PERM,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}