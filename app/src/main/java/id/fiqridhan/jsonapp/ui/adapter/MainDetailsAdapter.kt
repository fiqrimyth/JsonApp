package id.fiqridhan.jsonapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Comment
import java.lang.Exception

class MainDetailsAdapter(
    private val commentList: ArrayList<Comment> = ArrayList()
) : RecyclerView.Adapter<MainDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val comment = commentList[position]
            holder.tvTitle.text = comment.name
            holder.tvDesc.text = comment.body
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitleComment)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDescComment)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putItems(item:ArrayList<Comment>){
        commentList.addAll(item)
        notifyDataSetChanged()
    }
}