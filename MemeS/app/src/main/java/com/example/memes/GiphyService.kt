package com.example.memes

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface GiphyService {

    @GET("memes")
    suspend fun getMemes(): List<Meme>

    @POST("memes")
    suspend fun postMeme(@Body meme: Meme)
}
