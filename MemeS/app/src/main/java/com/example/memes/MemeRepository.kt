package com.example.memes

import android.content.Context

class MemeRepository(context: Context, private val apiService: GiphyService) {

    private val memeDao = MemeDatabase.getDatabase(context).memeDao()

    // Fetch memes: online first, fallback offline
    suspend fun fetchMemes(): List<Meme> {
        return try {
            val apiMemes = apiService.getMemes()
            apiMemes.forEach { memeDao.insertMeme(it) } // cache
            apiMemes
        } catch (e: Exception) {
            memeDao.getAllMemes()
        }
    }

    suspend fun addMeme(meme: Meme) {
        memeDao.insertMeme(meme)
    }

    suspend fun syncMemes() {
        val unsynced = memeDao.getUnsyncedMemes()
        unsynced.forEach { meme ->
            try {
                apiService.postMeme(meme)
                memeDao.updateMeme(meme.copy(isSynced = true))
            } catch (_: Exception) { /* keep unsynced */ }
        }
    }
}
