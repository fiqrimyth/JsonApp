@file:Suppress("DEPRECATION")

package id.fiqridhan.jsonapp.utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager

object AppUtils {
    fun isNetworkPresent(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun defaultDialog(context: Context, message: String) {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setMessage(message)
        builder1.setCancelable(true)
        builder1.setNegativeButton(
            "OK"
        ) { dialog, _ -> dialog.cancel() }
        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}