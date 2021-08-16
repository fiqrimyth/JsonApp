package id.fiqridhan.jsonapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Album(
    @SerializedName("userId")
    val userID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
) : Serializable