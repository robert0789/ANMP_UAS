package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.content.Intent
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
import org.json.JSONObject


class SignUpViewModel(application: Application): AndroidViewModel(application) {
    val loadingLD = MutableLiveData<Boolean>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    var successSignUpLD = MutableLiveData<Boolean>(false)
    var statusLD = MutableLiveData<String>()
    val TAG = "volleyFilmTag"
    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun signUp(username: String, firstName: String, lastName:String,
               email: String, password: String, imageUrl:String){

        loadingLD.value = true
        userLoadErrorLD.value = false
        val url = "http://10.0.2.2/anmp/uts/signup.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK"){
                    successSignUpLD.value = true
                }
                statusLD.value = obj.getString("message")

                Log.d("signup", it.toString())
            },
            {
                Log.d("signup", it.toString())

            }

        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                Log.d("first_name", firstName)
                params["username"] = username
                params["first_name"] = firstName
                params["last_name"] = lastName
                params["email"] = email
                params["password"] = password
                params["img_url"] = imageUrl
                return params
            }
        }

        stringRequest.tag =TAG
        queue?.add(stringRequest)


    }
}