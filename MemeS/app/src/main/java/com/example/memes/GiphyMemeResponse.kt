package com.example.memes

import com.squareup.moshi.Json

data class GiphyMemeResponse(
    val data: List<GifData>
)

data class GifData(
    val id: String,
    val title: String,
    @Json(name = "images") val images: Images
)

data class Images(
    @Json(name = "original") val original: Original
)

data class Original(
    val url: String
)
