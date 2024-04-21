package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robert.anmp_uts.model.User
import org.json.JSONObject

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val userIDLD = MutableLiveData<Int>()
    val loadingLD = MutableLiveData<Boolean>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val statusLD = MutableLiveData<String>()

    // Reference to the SharedViewModel
    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(SharedViewModel::class.java)
    }
    val TAG = "volleyUserTag"
    private var queue: RequestQueue?=null


    //namanya bisa fetch, load
    fun checkLogin(username: String, password: String){

        if (username.isBlank() || password.isBlank()){
            return
        }

        loadingLD.value = true
        userLoadErrorLD.value = false

        val url = "http://10.0.2.2/anmp/uts/login.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK"){
                    userIDLD.value = obj.getInt("data")
                    Log.d("show_volley_user", userIDLD.value.toString())
                    loadingLD.value = false
                    statusLD.value = "Login successful"
                    Log.d("show_volley", statusLD.value.toString())


                    //cache user id in shared view model
                    sharedViewModel.setUserId(userIDLD.value!!)
                }
            },
            {
                Log.d("show_volley", it.toString())

            }

        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag =TAG
        queue?.add(stringRequest)

    }
}