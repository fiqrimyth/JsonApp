package id.fiqridhan.jsonapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Comment
import id.fiqridhan.jsonapp.network.viewmodel.MainDetailsVm
import id.fiqridhan.jsonapp.ui.adapter.MainDetailsAdapter
import id.fiqridhan.jsonapp.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_details.*
import java.lang.Exception

class MainDetailsActivity : AppCompatActivity() {

    private val viewModel = MainDetailsVm()

    private lateinit var lManager: LinearLayoutManager
    private lateinit var detailAdapter: MainDetailsAdapter

    private var comment: ArrayList<Comment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_details)

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
        ivBack.setOnClickListener { onBackPressed() }

        val id: String = intent.extras?.getString("userId").toString()
        val username: String = intent.extras?.getString("username").toString()
        val title: String = intent.extras?.getString("title").toString()
        val desc: String = intent.extras?.getString("body").toString()
        val email: String = intent.extras?.getString("email").toString()
        val address: String = intent.extras?.getString("address").toString()
        val company: String = intent.extras?.getString("company").toString()

        tvUserNameDetail.text = username
        tvTitleDetail.text = title
        tvDescDetail.text = desc

        lManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
        detailAdapter = MainDetailsAdapter(comment)

        rvDetails.layoutManager = lManager
        rvDetails.adapter = detailAdapter
        rvDetails.setHasFixedSize(true)

        viewModel.getListComment()
        viewModel.listComment.observe(this, { listComment ->
            comment = listComment as ArrayList
            detailAdapter.putItems(comment)
        })

        tvUserNameDetail.setOnClickListener {
            val intent = Intent(applicationContext, UserDetailActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("username", username)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            intent.putExtra("company", company)
            startActivity(intent)
        }
    }
}