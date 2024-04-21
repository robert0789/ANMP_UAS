package com.robert.anmp_uts.model
import com.google.gson.annotations.SerializedName
import com.robert.anmp_uts.model.News
import java.sql.Date

data class Article(
    val id: Int,
    val paragraph : String,
    @SerializedName("news_id")
    val newsID : Int,
)

