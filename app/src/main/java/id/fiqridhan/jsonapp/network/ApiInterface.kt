package id.fiqridhan.jsonapp.network

import id.fiqridhan.jsonapp.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/posts")
    fun getPostAsync(): Deferred<Response<List<Post>>>

    @GET("/comments")
    fun getCommentAsync(): Deferred<Response<List<Comment>>>

    @GET("/albums")
    fun getAlbumAsync(): Deferred<Response<List<Album>>>

    @GET("/photos")
    fun getPhotosAsync(@Query("albumId") albumId: Int): Deferred<Response<List<Photos>>>

    @GET("/users")
    fun getUserAsync(): Deferred<Response<List<User>>>
}