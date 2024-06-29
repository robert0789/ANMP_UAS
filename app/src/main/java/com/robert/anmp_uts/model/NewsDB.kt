package com.robert.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.robert.anmp_uts.util.DB_NAME
import com.robert.anmp_uts.util.MIGRATION_1_2
import com.robert.anmp_uts.util.MIGRATION_2_3

@Database(entities = [News::class, User::class], version =  3)
abstract class NewsDB:RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun userDao() : UserDao

    companion object {
        @Volatile private var instance: NewsDB ?= null
        private val LOCK = Any()

        fun getInstance(context: Context): NewsDB {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDB::class.java,
                DB_NAME).addMigrations(MIGRATION_1_2,MIGRATION_2_3).build()

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