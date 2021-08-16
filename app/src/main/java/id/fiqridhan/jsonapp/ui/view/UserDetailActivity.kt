package id.fiqridhan.jsonapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Album
import id.fiqridhan.jsonapp.model.Photos
import id.fiqridhan.jsonapp.model.User
import id.fiqridhan.jsonapp.network.viewmodel.UserVm
import id.fiqridhan.jsonapp.ui.adapter.AlbumAdapter
import id.fiqridhan.jsonapp.ui.adapter.AlbumListener
import id.fiqridhan.jsonapp.utils.AppUtils
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.lang.Exception

class UserDetailActivity : AppCompatActivity(), AlbumListener {

    private val viewModel = UserVm()

    private lateinit var lManager: LinearLayoutManager
    private lateinit var albumAdapter: AlbumAdapter

    private var albums: ArrayList<Album> = ArrayList()
    private var photos: ArrayList<Photos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

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

        val username: String = intent.extras?.getString("username").toString()
        val email: String = intent.extras?.getString("email").toString()
        val address: String = intent.extras?.getString("address").toString()
        val company: String = intent.extras?.getString("company").toString()

        tvUserDetail.text = username
        tvEmail.text = email
        tvAddress.text = address
        tvCompanyDetail.text = company

        lManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        albumAdapter = AlbumAdapter(this, albums, photos, this)

        rvAlbums.layoutManager = lManager
        rvAlbums.adapter = albumAdapter
        rvAlbums.setHasFixedSize(true)

        viewModel.getListAlbum()
        viewModel.getListPhotos(1)
        viewModel.listAlbum.observe(this, { listAlbum ->
            albums = listAlbum as ArrayList
            albumAdapter.putItem(albums)
        })

        viewModel.listPhotos.observe(this, { listPhotos ->
            photos = listPhotos as ArrayList
            albumAdapter.putItems(photos)
        })

    }

    override fun onClickAlbumItem(photos: Photos) {
        TODO("Not yet implemented")
    }
}