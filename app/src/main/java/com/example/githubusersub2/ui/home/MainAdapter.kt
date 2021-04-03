package com.example.githubusersub2.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersub2.R

import com.example.githubusersub2.databinding.ItemUserBinding
import com.example.githubusersub2.interfaces.UserInterface
import com.example.githubusersub2.model.User

class MainAdapter(private val context: Context) : ListAdapter<User, MainAdapter.ViewHolder>(DiffCallBack()) {

    var userInterface: UserInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemUserBinding, private val context: Context) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Glide.with(binding.root).load(user.userImage).into(binding.ivUser)
            binding.apply {
                tvUsername.text = user.userName
                tvUserCompany.text = user.company
                        ?: context.resources.getString(R.string.no_company)
                tvFollower.text = String.format(context.resources.getString(R.string.n_followers), user.followers)
                tvFollowing.text = String.format(context.resources.getString(R.string.n_following), user.following)

                clUserRoot.setOnClickListener {
                    userInterface?.onUserClicked(binding.root, user)
                }
            }
        }
    }

}

class DiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.userName == newItem.userName

}
