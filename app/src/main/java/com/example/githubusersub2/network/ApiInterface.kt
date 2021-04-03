package com.example.githubusersub2.network

import com.example.githubusersub2.model.User
import com.example.githubusersub2.util.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/users")
    @Headers(API_KEY)
    fun search(
            @Query("q") username: String
    ): Call<ApiResponse>

    @GET("users/{username}")
    @Headers(API_KEY)
    fun getDetail(
            @Path("username") username: String
    ): Call<User>

    @GET("users/{username}/followers")
    @Headers(API_KEY)
    fun getFollowers(
            @Path("username") username: String
    ): Call<Array<User>>

    @GET("users/{username}/following")
    @Headers(API_KEY)
    fun getFollowing(
            @Path("username") username: String
    ): Call<Array<User>>
}