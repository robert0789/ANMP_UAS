package com.robert.anmp_uts.view

import android.view.View

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

