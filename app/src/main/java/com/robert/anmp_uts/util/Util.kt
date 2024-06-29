package com.robert.anmp_uts.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.robert.anmp_uts.model.NewsDB

val DB_NAME = "newsDB"

fun buildDb(context: Context): NewsDB{
    val db = NewsDB.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the news table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS news (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                author INTEGER NOT NULL,
                description TEXT NOT NULL,
                imageURL TEXT NOT NULL,
                date INTEGER NOT NULL,
                FOREIGN KEY(author) REFERENCES user(id) ON DELETE CASCADE
            )
        """)
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // category
        database.execSQL("ALTER TABLE news ADD COLUMN category " +
                "TEXT DEFAULT 'unlisted' NOT NULL")

        //detail berita
        database.execSQL("ALTER TABLE news ADD COLUMN detail TEXT DEFAULT 'detail' NOT NULL")

    }
}
