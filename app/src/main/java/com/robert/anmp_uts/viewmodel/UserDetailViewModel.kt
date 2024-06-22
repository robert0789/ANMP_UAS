package com.robert.anmp_uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserDetailViewModel (application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLD = MutableLiveData<User>()

    fun addTodo(user: User) { //bisa pake list
        launch {
            val db = buildDb(
                getApplication()
            )
            db.userDao().insertAll(user)
        }
    }

    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().loginUser(username,password))
        }
    }

    fun fetch(id:Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }

    fun update(user: User) {
        launch {
            buildDb(getApplication()).userDao().updateUser(user)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}