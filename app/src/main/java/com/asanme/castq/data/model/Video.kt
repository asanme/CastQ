package com.asanme.castq.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_urls")
data class Video(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String
)