package id.fiqridhan.jsonapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Photos
import java.lang.Exception

interface AlbumListener {
    fun onClickAlbumItem(photos: Photos)
}

class PhotoAdapter(
    private val context: Context,
    private val photoList: ArrayList<Photos> = ArrayList(),
    private val listener: AlbumListener
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rivPhoto: RoundedImageView = itemView.findViewById(R.id.rivPhoto)
        var llAdapter: LinearLayout = itemView.findViewById(R.id.llAlbums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val photo = photoList[position]
            Glide
                .with(context)
                .load(photo.url)
                .into(holder.rivPhoto)

            holder.llAdapter.setOnClickListener {
                listener.onClickAlbumItem(photo)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}