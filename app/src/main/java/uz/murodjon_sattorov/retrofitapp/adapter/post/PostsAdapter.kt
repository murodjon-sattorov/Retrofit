package uz.murodjon_sattorov.retrofitapp.adapter.post

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.murodjon_sattorov.retrofitapp.R
import uz.murodjon_sattorov.retrofitapp.databinding.PostViewItemBinding
import uz.murodjon_sattorov.retrofitapp.model.post.PostModel


/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 8/15/2021
 * @project Retrofit App
 */
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var postData = emptyList<PostModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PostViewHolder(var binding: PostViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(postModel: PostModel) {
            Glide.with(binding.postUserImage.context).load(postModel.owner.picture)
                .into(binding.postUserImage)
            Glide.with(binding.postImage.context).load(postModel.image)
                .into(binding.postImage)
            binding.postUserName.text = postModel.owner.firstName
            binding.postLikeCount.text = postModel.likes.toString() + " likes"
            binding.postUserNameWithTitle.text =
                Html.fromHtml("<strong>" + postModel.owner.firstName + "</strong>" + "  " + postModel.text)
            binding.postDate.text = postModel.publishDate

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostViewHolder {
        return PostViewHolder(
            PostViewItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.post_view_item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostViewHolder, position: Int) {
        holder.bindData(postData[position])
    }

    override fun getItemCount(): Int {
        postData.let {
            return it.size
        }
    }
}