package com.robert.anmp_uts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo("username")
    var username: String,
    @ColumnInfo("firstName")
    var firstName: String,
    @ColumnInfo("lastName")
    var lastName: String,
    @ColumnInfo("email")
    var email: String,
    @ColumnInfo("password")
    var password: String,
    @ColumnInfo("imageURL")
    var imageURL: String

){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}
