package id.fiqridhan.jsonapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Post
import id.fiqridhan.jsonapp.model.User
import id.fiqridhan.jsonapp.network.viewmodel.MainVm
import id.fiqridhan.jsonapp.utils.AppUtils
import id.fiqridhan.jsonapp.ui.adapter.MainAdapter
import id.fiqridhan.jsonapp.ui.adapter.PostListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), PostListener {

    private val viewModel = MainVm()

    private lateinit var lManager: LinearLayoutManager
    private lateinit var mainAdapter: MainAdapter

    private var post: ArrayList<Post> = ArrayList()
    private var user: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            if (AppUtils.isNetworkPresent(this)) {
                initView()
            } else {
                AppUtils.defaultDialog(
                    this,
                    "No Internet Connection"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initView() {
        lManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        mainAdapter = MainAdapter(post, user, this)

        rvMain.layoutManager = lManager
        rvMain.adapter = mainAdapter
        rvMain.setHasFixedSize(true)

        viewModel.getListPost()
        viewModel.getListUser()
        viewModel.listPost.observe(this, { listPost ->
            post = listPost as ArrayList
            mainAdapter.putItems(post)
        })

        viewModel.listUser.observe(this, { listUser ->
            user = listUser as ArrayList
            mainAdapter.putItem(user)
        })
    }

    override fun onClickPostItem(post: Post, user: User) {
        val intent = Intent(applicationContext, MainDetailsActivity::class.java)
        intent.putExtra("userId", post.userID)
        intent.putExtra("username", user.name)
        intent.putExtra("email", user.email)
        intent.putExtra("address", user.address.city)
        Log.d("address", user.address.toString())
        intent.putExtra("company", user.company.name)
        intent.putExtra("title", post.title)
        intent.putExtra("body", post.body)
        startActivity(intent)
    }
}