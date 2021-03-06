package com.example.lostandfoundipb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lostandfoundipb.R
import com.example.lostandfoundipb.Utils.formatDate
import com.example.lostandfoundipb.Utils.Global.Companion.URL_POST
import com.example.lostandfoundipb.Utils.relativeTime
import com.example.lostandfoundipb.retrofit.models.Post
import com.example.lostandfoundipb.ui.PostDetailActivity
import kotlinx.android.synthetic.main.item_post.view.*
import org.jetbrains.anko.startActivity

class PostAdapter (private val posts: List<Post.Post>,
                   private val context: Context)
    : RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, int: Int): PostViewHolder {
        val viewHolder = PostViewHolder(
            LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_post, viewGroup, false)
        )

        viewHolder.itemView.layout_post.setOnClickListener {
            val position = viewHolder.adapterPosition
            viewGroup.context?.startActivity<PostDetailActivity>("post" to posts[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindItem(posts[position], context)
    }

}

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val title = view.post_title
    private val description = view.post_desc
    private val picture = view.post_image
    private val time = view.post_time
    private val lostBadge = view.post_lost
    private val foundBadge = view.post_found


    fun bindItem(post: Post.Post, context: Context) {
        foundBadge.visibility = View.GONE
        lostBadge.visibility = View.GONE
        title.text = post.title
        description.text = post.description
        time.text = relativeTime(post.updated_at)
        if(post.type){
            foundBadge.visibility = View.VISIBLE
        }
        else {
            lostBadge.visibility = View.VISIBLE
        }
        post.item.picture.let { setImage(it, context) }
    }

    private fun setImage(url: String, context: Context) {
        Glide.with(context)
                .load(URL_POST+url)
                .apply(RequestOptions().placeholder(R.drawable.ic_baseline_image_24))
                .apply(RequestOptions().centerCrop())
                .into(picture)
    }
}