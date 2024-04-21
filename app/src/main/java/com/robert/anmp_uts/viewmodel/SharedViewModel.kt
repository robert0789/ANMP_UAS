package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val userId = MutableLiveData<Int>()

    fun setUserId(userId: Int) {
        this.userId.value = userId
        Log.d("shared view model user ID", this.userId.value.toString())

    }
}

