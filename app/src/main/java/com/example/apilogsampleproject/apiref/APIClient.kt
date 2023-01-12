package com.example.apilogsampleproject.apiref

import com.rv.apilog.apiclient.interceptor.ApiLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private var retrofit: Retrofit? = null
    val client: Retrofit
        get() {

            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(ApiLogInterceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://catfact.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit!!
        }
}