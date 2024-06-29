package com.robert.anmp_uts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: News)

    @Query("SELECT * FROM news")
    fun selectAllNews(): List<News>
    @Query("SELECT * FROM news WHERE id= :id")
    fun selectNews(id:Int): News

    @Delete
    fun deleteNews(news: News)

    @Update
    fun updateNews(news: News)

    @Query("SELECT COUNT(*) FROM news WHERE id = :id")
    fun newsExist(id: Int): Int

    @Query("SELECT u.id, u.username FROM user u INNER JOIN news n ON u.id = n.author")
    fun selectAuthorsName() : List<NewsAuthor>

    @Query("SELECT username FROM user where id =:id")
    fun selectAuthorName(id: Int) : String

    @Query("DELETE FROM news")
    fun deleteAllNews()

    @Query("DELETE FROM sqlite_sequence")
    fun resetAutoIncrementNewsId()
}
