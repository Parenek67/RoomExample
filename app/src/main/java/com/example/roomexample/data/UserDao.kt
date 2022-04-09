package com.example.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomexample.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun readAllUsers(): LiveData<List<User>>

}