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
    val userIDLD = MutableLiveData<Int>(0)
    val loadingLD = MutableLiveData<Boolean>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val statusLD = MutableLiveData<String>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }



    //namanya bisa fetch, load
    fun checkLogin(username: String, password: String){

        if (username.isBlank() || password.isBlank()){
            return
        }

        loadingLD.value = true
        userLoadErrorLD.value = false
        queue?.cancelAll(TAG)

        if (queue == null) {
            queue = Volley.newRequestQueue(getApplication())
        }
        val url = "http://10.0.2.2/anmp/uts/login.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK"){
                    userIDLD.value = obj.getInt("data")
                    Log.d("show_volley_user", userIDLD.value.toString())
                    statusLD.value = "Login successful"
                    Log.d("show_volley", statusLD.value.toString())

                }

                else{
                    statusLD.value = "username or password is incorrect"

                }
                Log.d("show_volley_user", userIDLD.value.toString())

            },
            {
                Log.d("show_volley", it.toString())
                Log.d("show_volley_user", userIDLD.value.toString())


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