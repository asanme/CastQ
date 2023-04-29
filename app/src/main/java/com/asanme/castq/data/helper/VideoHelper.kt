package com.asanme.castq.data.helper

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.asanme.castq.data.database.VideoDatabase

object VideoHelper {
    fun getVideoDatabase(context: Context): VideoDatabase {
        return databaseBuilder(
            context,
            VideoDatabase::class.java,
            "video_database"
        ).build()
    }
}