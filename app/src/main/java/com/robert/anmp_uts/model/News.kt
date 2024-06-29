package com.robert.anmp_uts.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import com.robert.anmp_uts.model.Article


@Entity(
    tableName = "news",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("author"),
        onDelete = ForeignKey.CASCADE
    )]
)

data class News(
    val title: String,
    val author: Int,
    val description: String,
    val imageURL : String,
    val date: Int,
    val detail: String,
    val category: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}



