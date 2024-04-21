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
import com.robert.anmp_uts.model.News
import org.json.JSONObject

class NewsListViewModel(application: Application): AndroidViewModel(application) {

    //life data berupa arr data supaya adapter bisa nerima data
    val newsLD = MutableLiveData<ArrayList<News>>()
    val loadingLD = MutableLiveData<Boolean>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun refresh(){

        loadingLD.value = true
        newsLoadErrorLD.value = false

        val url = "http://10.0.2.2/anmp/uts/get_news.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                //it berisi json string
                Log.d("show_volley", it)
                //object untuk gson
                val obj = JSONObject(it)
                if(obj.getString("result")=="OK"){
                    val sType = object: TypeToken<List<News>>() {}.type
                    val result = Gson().fromJson<List<News>>(obj.getJSONArray("data").toString(), sType)
                    newsLD.value = result as ArrayList<News>

                }




            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)

    }
}