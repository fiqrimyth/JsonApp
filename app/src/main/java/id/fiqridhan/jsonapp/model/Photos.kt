package id.fiqridhan.jsonapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photos(
    @SerializedName("albumId")
    val albumID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailURL: String
) : Serializable