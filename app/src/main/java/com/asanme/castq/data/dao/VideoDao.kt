package com.asanme.castq.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.asanme.castq.data.model.Video

@Dao
interface VideoDao {

    @Insert
    suspend fun insert(video: Video)

    @Update
    suspend fun update(video: Video)

    @Delete
    suspend fun delete(video: Video)

    @Query("SELECT * FROM video_urls")
    suspend fun getAllVideos(): List<Video>

}