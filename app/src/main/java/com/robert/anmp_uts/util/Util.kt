package com.robert.anmp_uts.util

import android.content.Context
import com.robert.anmp_uts.model.NewsDB

val DB_NAME = "newsDB"

fun buildDb(context: Context): NewsDB{
    val db = NewsDB.buildDatabase(context)
    return db
}