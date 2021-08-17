package uz.murodjon_sattorov.retrofitapp.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qintong.library.InsLoadingView
import uz.murodjon_sattorov.retrofitapp.R
import uz.murodjon_sattorov.retrofitapp.databinding.UserViewItemBinding
import uz.murodjon_sattorov.retrofitapp.model.user.UserModel

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 8/15/2021
 * @project Retrofit App
 */

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var userData = emptyList<UserModel>().shuffled()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class UserViewHolder(private val binding: UserViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(userModel: UserModel) {
            binding.userName.text = userModel.firstName
            Glide.with(binding.userImage).load(userModel.picture).into(binding.userImage)

            binding.userImage.setOnClickListener {
                binding.userImage.status = InsLoadingView.Status.LOADING
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserViewItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.user_view_item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(userData[position])
    }

    override fun getItemCount(): Int {
        userData.let {
            return it.size
        }
    }
}