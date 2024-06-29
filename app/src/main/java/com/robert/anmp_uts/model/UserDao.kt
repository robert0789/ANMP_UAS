package com.robert.anmp_uts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user WHERE id= :id")
    fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun loginUser(username: String, password: String): User?

    @Delete
    fun deleteUser(user: User)

    @Query("UPDATE user SET firstName=:firstName, lastName=:lastName WHERE id=:id")
    fun changeName(id: Int,firstName:String, lastName:String)

    @Query("UPDATE user SET password=:password WHERE id=:id")
    fun changePassword(id: Int,password:String)

    @Update
    fun updateUser(user: User)}