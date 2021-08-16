package id.fiqridhan.jsonapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Album
import id.fiqridhan.jsonapp.model.Photos
import java.lang.Exception

class AlbumAdapter(
    private val mContext: Context,
    private val albumList: ArrayList<Album> = ArrayList(),
    private val photoList: ArrayList<Photos> = ArrayList(),
    private val listener: AlbumListener
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val album = albumList[position]
            holder.tvTitle.text = album.title
            holder.rvAlbum.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = PhotoAdapter(mContext, photoList, listener)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putItems(item: ArrayList<Photos>) {
        photoList.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putItem(item: ArrayList<Album>) {
        albumList.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitleAlbum)
        var rvAlbum: RecyclerView = itemView.findViewById(R.id.rvPhotos)
    }

}