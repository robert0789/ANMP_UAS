package com.robert.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.robert.anmp_uts.util.DB_NAME

@Database(entities = arrayOf(User::class), version =  1)
abstract class NewsDB:RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: NewsDB ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDB::class.java,
                DB_NAME).build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }


    }

}