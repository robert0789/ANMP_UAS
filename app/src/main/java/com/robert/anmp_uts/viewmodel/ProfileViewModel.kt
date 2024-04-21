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
import org.json.JSONObject

class ProfileViewModel(application: Application): AndroidViewModel(application){
    val userLD = MutableLiveData<User>()
    private var queue: RequestQueue?=null
    val TAG = "volleyProfileTag"

    fun fetch(userID : Int) {

        val url = "http://10.0.2.2/anmp/uts/get_profile.php?id=$userID"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                //it berisi json string
                val obj = JSONObject(it);
                if(obj.getString("result") == "OK"){
                    val sType = object : TypeToken<User>() {}.type
                    val result = Gson().fromJson<User>(obj.getJSONObject("data").toString(), sType)

                    userLD.value = result as User
                }

                Log.d("show_volley", it)
            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
    }

    fun changePassword(oldPassword: String, newPassword : String, repeatNewPassword : String):String{
       var status = "Change password successfully"
        if(oldPassword != userLD.value!!.password){
            status = "Old password is incorrect"
        }
        else if(newPassword == oldPassword){
            status = "New password is don't be same as old password"
        }

        else if(newPassword != repeatNewPassword){
            status =  "New password and repeat new password is not same"
        }

        else{
            val url = "http://10.0.2.2/anmp/uts/set_profile_change_password.php"
            queue = Volley.newRequestQueue(getApplication())

            val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    val obj = JSONObject(it);
                    if(obj.getString("result") == "OK"){
                        val sType = object : TypeToken<User>() {}.type
                        val result = Gson().fromJson<User>(obj.getJSONObject("data").toString(), sType)
                        Log.d("show_volley_profile", result.toString())

                        userLD.value = result as User

                        status = "Change password successful"
                        Log.d("show_volley_profile", result.toString())
                        Log.d("show_volley_status", "password successfully ")

                    }

                    else{
                        status = obj.getString("message")
                    }

//                    loadingLD.value = false
                    Log.d("show_volley", status)


                },
                {
                    Log.d("show_volley", it.toString())
                    status = it.toString()
                }

            ){
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = userLD.value!!.id.toString()
                    params["new_password"] = newPassword
                    return params
                }
            }
            stringRequest.tag =TAG
            queue?.add(stringRequest)
        }

        return status

    }

    fun changeName(firstName: String, lastName : String):String{
        var status = "Change name successfully"
        if(firstName == userLD.value!!.firstName && lastName == userLD.value!!.lastName){
            status = "The name don't change anything"
        }
        else{
            val url = "http://10.0.2.2/anmp/uts/set_profile_change_name.php"
            queue = Volley.newRequestQueue(getApplication())

            val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    val obj = JSONObject(it);
                    if(obj.getString("result") == "OK"){
                        val sType = object : TypeToken<User>() {}.type
                        val result = Gson().fromJson<User>(obj.getJSONObject("data").toString(), sType)
                        Log.d("show_volley_profile", result.toString())

                        userLD.value = result as User

                        status = "Change name successful"
                        Log.d("show_volley_profile", result.toString())
                        Log.d("show_volley_status", "password successfully ")

                    }

                    else{
                        status = obj.getString("message")
                    }

//                    loadingLD.value = false
                    Log.d("show_volley", status)


                },
                {
                    Log.d("show_volley", it.toString())
                    status = it.toString()
                }

            ){
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = userLD.value!!.id.toString()
                    params["first_name"] = firstName
                    params["last_name"] = lastName
                    return params
                }
            }
            stringRequest.tag =TAG
            queue?.add(stringRequest)
        }

        return status

    }


}