package com.example.memes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes")
data class Meme(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val caption: String,
    val imageUrl: String,
    val lat: Double? = null,
    val lng: Double? = null,
    val isSynced: Boolean = false
)
