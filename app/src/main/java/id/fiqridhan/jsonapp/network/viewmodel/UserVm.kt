package id.fiqridhan.jsonapp.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fiqridhan.jsonapp.model.Album
import id.fiqridhan.jsonapp.model.Photos
import id.fiqridhan.jsonapp.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class UserVm : ViewModel() {
    private val apiInterface = ApiFactory.getData
    var listAlbum = MutableLiveData<List<Album>>()
    var listPhotos = MutableLiveData<List<Photos>>()

    fun getListAlbum() {
        GlobalScope.launch(Dispatchers.Main) {
            val getList = apiInterface.getAlbumAsync()
            try {
                val response = getList.await()
                if (response.isSuccessful) {
                    response.body()?.also {
                        listAlbum.value = it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getListPhotos(albumId:Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val getList = apiInterface.getPhotosAsync(albumId)
            try {
                val response = getList.await()
                if (response.isSuccessful) {
                    response.body()?.also {
                        listPhotos.value = it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}