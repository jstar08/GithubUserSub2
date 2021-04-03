package com.example.githubusersub2.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubusersub2.R
import com.example.githubusersub2.databinding.ActivityDetailBinding
import com.example.githubusersub2.model.User
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val USER = "user"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: DetailAdapter
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivActionBack.setOnClickListener { onBackPressed() }

        if (!intent.hasExtra(USER)) {
            onBackPressed()
        }

        user = intent.getParcelableExtra<User>(USER) as User

        Glide.with(this).load(user.userImage).into(binding.ivAvatar)
        binding.tvName.text = user.userName
        binding.tvCompany.text = user.company ?: resources.getString(R.string.no_company)
        binding.tvLocation.text = user.location ?: resources.getString(R.string.no_location)
        binding.tvRepository.text = resources.getString(R.string.repository, user.repository)

        initViewPager()
    }

    private fun initViewPager() {
        adapter = DetailAdapter(this, user)
        binding.viewPagerDetail.adapter = adapter
        binding.viewPagerDetail.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(
                binding.tabLayoutDetail,
                binding.viewPagerDetail
        ) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.followers, user.followers)
                1 -> tab.text = resources.getString(R.string.following, user.following)
            }
        }.attach()
    }


}