package com.example.apilogsampleproject.apiref

import com.example.apilogsampleproject.mode.CatFacts
import retrofit2.Call
import retrofit2.http.GET




interface ApiInterface {

    @GET("facts")
    fun getCatFacts(): Call<CatFacts>

}