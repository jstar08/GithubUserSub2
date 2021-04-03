package com.example.githubusersub2.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.githubusersub2.R
import com.example.githubusersub2.databinding.ActivityMainBinding
import com.example.githubusersub2.interfaces.UserInterface
import com.example.githubusersub2.model.User
import com.example.githubusersub2.ui.detail.DetailActivity
import com.example.githubusersub2.util.hide
import com.example.githubusersub2.util.show


class MainActivity : AppCompatActivity(), UserInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    private var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarHome)

        viewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        adapter = MainAdapter(this)
        adapter.userInterface = this
        binding.rvMain.adapter = adapter

        if (viewModel.firstTime.value == null) {
            viewModel.setUser("ivan")
        }
        setViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_locale_pref) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()) {
                        users.clear()
                        viewModel.setUser(query)
                        setViewModel()
                        emptyState(false)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        searchView.setOnCloseListener {
            users.clear()
            viewModel.setUser("ivan")
            setViewModel()
            false
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setViewModel() {
        viewModel.getUsers().observe(this, { result ->
            if (result != null && result.size > 0) {
                users = result
                adapter.submitList(users)
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.state.observeForever {
            it["loading"]?.let { isLoading ->
                loadingState(isLoading)
            }
            it["empty"]?.let { isEmpty ->
                loadingState(isEmpty)
            }
            it["error"]?.let { isError ->
                if (isError) viewModel.state.postValue(mapOf("error" to false))
            }
        }
    }

    private fun emptyState(state: Boolean) {
        if (state) {
            binding.apply {
                rvMain.hide()
            }
        } else {
            binding.apply {
                rvMain.show()
            }
        }
        binding.progressBarHome.hide()
    }

    private fun loadingState(state: Boolean) {
        if (state) {
            binding.progressBarHome.show()
        } else {
            binding.progressBarHome.hide()
        }
    }

    override fun onUserClicked(view: View, user: User) {
        val intent = Intent(
                this, DetailActivity::class.java
        )
        intent.putExtra(DetailActivity.USER, user)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}