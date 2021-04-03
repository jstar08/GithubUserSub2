package com.example.githubusersub2.network

import com.example.githubusersub2.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @Expose
        @SerializedName("total_count") val totalCount: Int,
        @Expose
        @SerializedName("incomplete_results") val incompleteResult: Boolean,
        @Expose
        @SerializedName("items") val users: ArrayList<User>
)