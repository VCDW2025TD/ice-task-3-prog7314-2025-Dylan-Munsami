package com.example.memes

import androidx.room.*

@Dao
interface MemeDao {

    @Query("SELECT * FROM memes")
    suspend fun getAllMemes(): List<Meme>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeme(meme: Meme)

    @Update
    suspend fun updateMeme(meme: Meme)

    @Query("SELECT * FROM memes WHERE isSynced = 0")
    suspend fun getUnsyncedMemes(): List<Meme>
}

