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
import com.robert.anmp_uts.model.Article
import com.robert.anmp_uts.model.News
import org.json.JSONObject

class NewsDetailViewModel(application: Application): AndroidViewModel(application) {
    //life data berupa arr data supaya adapter bisa nerima data
    val articleLD = MutableLiveData<ArrayList<Article>>()
    val loadingLD = MutableLiveData<Boolean>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun get_article(newsID : Int){

        loadingLD.value = true
        newsLoadErrorLD.value = false


        val url = "http://10.0.2.2/anmp/uts/get_article.php?id=$newsID"
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
                    val sType = object: TypeToken<List<Article>>() {}.type
                    val result = Gson().fromJson<List<Article>>(obj.getJSONArray("data").toString(), sType)
                    articleLD.value = result as ArrayList<Article>
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