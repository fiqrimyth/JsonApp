package id.fiqridhan.jsonapp.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fiqridhan.jsonapp.model.Comment
import id.fiqridhan.jsonapp.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainDetailsVm : ViewModel() {
    private val apiInterface = ApiFactory.getData
    var listComment = MutableLiveData<List<Comment>>()

    fun getListComment() {
        GlobalScope.launch(Dispatchers.Main) {
            val getList = apiInterface.getCommentAsync()
            try {
                val response = getList.await()
                if (response.isSuccessful) {
                    response.body()?.also {
                        listComment.value = it
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}