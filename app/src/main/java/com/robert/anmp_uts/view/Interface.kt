package com.robert.anmp_uts.view

import android.view.View
import com.robert.anmp_uts.model.User

interface UserSignUpClickListener{
    fun onUserSignUpClick(v: View)
}

interface UserLoginClickListener{
    fun onUserLoginClick(v: View)
}

interface NewsDetailClickListener{
    fun onNewsDetailClickListener(v: View)
}

interface LoadAuthorNameListener{
    fun onLoadAuthorNameListener(authorId : Int) : String
}

interface NewsContentListener{
    fun onNextNewsContentListener(v: View)
    fun onPreviousNewsContentListener(v: View)

}

interface UserChangeNameClickListener {
    fun onUserChangeNameClick(view: View, user: User)
}

interface UserChangePasswordClickListener {
    fun onUserChangePasswordClick(view: View, user: User)
}

