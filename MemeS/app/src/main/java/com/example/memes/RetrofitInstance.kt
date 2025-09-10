package com.example.memes

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    val api: GiphyService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/") // GIPHY base URL
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GiphyService::class.java)
    }
}
