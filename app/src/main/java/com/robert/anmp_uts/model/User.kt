package com.robert.anmp_uts.model

import com.google.gson.annotations.SerializedName

data class User(
    val id : Int,
    val username: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val password: String,
    @SerializedName("image_url")
    val imageURL: String

)
