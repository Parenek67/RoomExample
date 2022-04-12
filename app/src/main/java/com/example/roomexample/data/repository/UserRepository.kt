package com.example.roomexample.data.repository

import androidx.lifecycle.LiveData
import com.example.roomexample.data.UserDao
import com.example.roomexample.data.model.User

class UserRepository(val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllUsers()

    suspend fun insert(user: User){
        userDao.insert(user)
    }

    suspend fun update(user: User){
        userDao.update(user)
    }

    suspend fun delete(user: User){
        userDao.delete(user)
    }
}