package com.asanme.castq.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asanme.castq.data.model.Video
import com.asanme.castq.data.dao.VideoDao

@Database(entities = [Video::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}