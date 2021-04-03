package com.example.githubusersub2.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubusersub2.model.User
import com.example.githubusersub2.ui.detail.followerFragment.FollowerFragment
import com.example.githubusersub2.ui.detail.followingFragment.FollowingFragment

class DetailAdapter(private val activity: DetailActivity, private val user: User) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment.newInstance(user)
            1 -> FollowingFragment.newInstance(user)
            else -> FollowerFragment.newInstance(user)
        }
    }

}