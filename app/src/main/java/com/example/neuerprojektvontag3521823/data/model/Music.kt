package com.example.neuerprojektvontag3521823.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Music(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Json(name = "artistName") val artistsName: String,
    @Json(name = "trackName") val trackName: String = "",
    @Json(name = "previewUrl") val previewUrl: String,
    @Json(name = "artworkUrl100") val artworkUrl100: String,
    @Json(name = "trackTime") val trackTime: Int = 0,
    @Json(name = "kind") val kind: String,

    ) {
    var trackTimeSecond: Double = (trackTime / 1000).toDouble()
    var liked: Boolean = false
    var forward10S: Int = 0
    var replay10S: Int = 0
    var musicPreview = 30
    var musicTimeStart: Int = 0

    var musicTimeEnd: Int = 0


}
