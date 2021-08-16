package id.fiqridhan.jsonapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(
    @SerializedName("postId")
    val postID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
) : Serializable
