package com.example.foodipy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foodipy.`interface`.GetDataService
import com.example.foodipy.entities.MealResponse
import com.example.foodipy.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")

        getDetailMeal(id!!)

        ib_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getDetailMeal(id: String) {
        val service = RetrofitInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getDetailMeal(id)
        call.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strMealThumb).into(riv_detail)

                tb_detail.title = response.body()!!.mealsEntity[0].strMeal
                tv_category_detail.text = response.body()!!.mealsEntity[0].strCategory

                val ingredient = "${response.body()!!.mealsEntity[0].strIngredient1}    ${response.body()!!.mealsEntity[0].strMeasure1}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient2}     ${response.body()!!.mealsEntity[0].strMeasure2}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient3}     ${response.body()!!.mealsEntity[0].strMeasure3}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient4}     ${response.body()!!.mealsEntity[0].strMeasure4}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient5}     ${response.body()!!.mealsEntity[0].strMeasure5}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient6}     ${response.body()!!.mealsEntity[0].strMeasure6}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient7}     ${response.body()!!.mealsEntity[0].strMeasure7}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient8}     ${response.body()!!.mealsEntity[0].strMeasure8}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient9}     ${response.body()!!.mealsEntity[0].strMeasure9}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient10}    ${response.body()!!.mealsEntity[0].strMeasure10}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient11}    ${response.body()!!.mealsEntity[0].strMeasure11}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient12}    ${response.body()!!.mealsEntity[0].strMeasure12}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient13}    ${response.body()!!.mealsEntity[0].strMeasure13}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient14}    ${response.body()!!.mealsEntity[0].strMeasure14}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient15}    ${response.body()!!.mealsEntity[0].strMeasure15}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient16}    ${response.body()!!.mealsEntity[0].strMeasure16}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient17}    ${response.body()!!.mealsEntity[0].strMeasure17}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient18}    ${response.body()!!.mealsEntity[0].strMeasure18}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient19}    ${response.body()!!.mealsEntity[0].strMeasure19}\n" +
                        "${response.body()!!.mealsEntity[0].strIngredient20}    ${response.body()!!.mealsEntity[0].strMeasure20}\n"

                tv_ingredients.text = ingredient
                tv_instructions.text = response.body()!!.mealsEntity[0].strInstructions
            }
        })
    }
}