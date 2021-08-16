package id.fiqridhan.jsonapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.fiqridhan.jsonapp.R
import id.fiqridhan.jsonapp.model.Post
import id.fiqridhan.jsonapp.model.User

interface PostListener {
    fun onClickPostItem(post: Post, user: User)
}

class MainAdapter(
    private val postList: ArrayList<Post> = ArrayList(),
    private var userList: ArrayList<User> = ArrayList(),
    private val listener: PostListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val post = postList[position]
            val user = userList[position]
            holder.tvTitle.text = post.title
            holder.tvDesc.text = post.body
            holder.tvUserName.text = user.name
            holder.tvCompany.text = user.company.name

            holder.llAdapter.setOnClickListener {
                listener.onClickPostItem(post, user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        var tvCompany: TextView = itemView.findViewById(R.id.tvCompanyName)
        var llAdapter: LinearLayout = itemView.findViewById(R.id.llAdapter)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putItems(items: ArrayList<Post>) {
        postList.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun putItem(item:ArrayList<User>){
        userList.addAll(item)
        notifyDataSetChanged()
    }
}