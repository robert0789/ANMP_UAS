package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLD = MutableLiveData<User>()


    fun fetch(userID:Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(userID))
        }
    }

    fun changeName(firstName:String, lastName:String, userID:Int) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().changeName(userID, firstName, lastName)
        }
    }

    fun changePassword(password:String, userID:Int) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().changePassword(userID, password)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}