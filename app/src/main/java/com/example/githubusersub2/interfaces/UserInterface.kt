package com.example.githubusersub2.interfaces

import android.view.View
import com.example.githubusersub2.model.User

interface UserInterface {
    fun onUserClicked(view: View, user: User)
}