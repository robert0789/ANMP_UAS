package com.robert.anmp_uts.model

import com.google.gson.annotations.SerializedName
import java.util.Date
import com.robert.anmp_uts.model.Article


data class News(
    val id: Int,
    val title: String,
    @SerializedName("username")
    val author: String,
    val description: String,
    @SerializedName("image_url")
    val imageURL : String,
    val date: Date
)


