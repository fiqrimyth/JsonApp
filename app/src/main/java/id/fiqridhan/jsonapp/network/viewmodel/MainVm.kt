package id.fiqridhan.jsonapp.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fiqridhan.jsonapp.model.Post
import id.fiqridhan.jsonapp.model.User
import id.fiqridhan.jsonapp.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainVm : ViewModel() {
    private val apiInterface = ApiFactory.getData
    var listPost = MutableLiveData<List<Post>>()
    var listUser = MutableLiveData<List<User>>()

    fun getListPost() {
        GlobalScope.launch(Dispatchers.Main) {
            val getList = apiInterface.getPostAsync()
            try {
                val response = getList.await()
                if (response.isSuccessful) {
                    response.body()?.also {
                        listPost.value = it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getListUser() {
        GlobalScope.launch(Dispatchers.Main) {
            val getList = apiInterface.getUserAsync()
            try {
                val response = getList.await()
                if (response.isSuccessful) {
                    response.body()?.also {
                        listUser.value = it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}