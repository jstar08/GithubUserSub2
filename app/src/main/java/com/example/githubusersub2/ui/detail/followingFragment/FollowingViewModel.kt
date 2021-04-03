package com.example.githubusersub2.ui.detail.followingFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusersub2.model.User
import com.example.githubusersub2.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Boolean>()
    val liveDataUser = MutableLiveData<ArrayList<User>>()
    val list = ArrayList<User>()

    fun getUserData(): MutableLiveData<ArrayList<User>> {
        return liveDataUser
    }

    fun setFollowing(username: String) {
        loadingState.postValue(true)
        val follower = ApiClient.getService().getFollowing(username)
        follower.enqueue(object : Callback<Array<User>> {
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                loadingState.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isNotEmpty()) {
                            for (element in it) {
                                getUserDetailData(element.userName)
                            }
                        }
                    }
                } else {
                    errorState.postValue(true)
                }
            }

            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                loadingState.postValue(false)
                errorState.postValue(true)
            }
        })
    }

    fun getUserDetailData(username: String) {
        loadingState.postValue(true)
        val userDetail = ApiClient.getService().getDetail(username)
        userDetail.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                loadingState.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        list.add(it)
                        liveDataUser.postValue(list)
                    }
                } else {
                    errorState.postValue(true)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loadingState.postValue(false)
                errorState.postValue(true)
            }
        })
    }
}