package com.example.githubusersub2.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        @Expose
        @SerializedName("id") var id: Int,
        @Expose
        @SerializedName("login") var userName: String,
        @Expose
        @SerializedName("name") var realName: String?,
        @Expose
        @SerializedName("company") var company: String?,
        @Expose
        @SerializedName("location") var location: String?,
        @Expose
        @SerializedName("public_repos") var repository: String?,
        @Expose
        @SerializedName("bio") var bio: String?,
        @Expose
        @SerializedName("followers") var followers: String?,
        @Expose
        @SerializedName("following") var following: String?,
        @Expose
        @SerializedName("followers_url") var urlFollowers: String?,
        @Expose
        @SerializedName("following_url") var urlFollowing: String?,
        @Expose
        @SerializedName("avatar_url") var userImage: String?
) : Parcelable