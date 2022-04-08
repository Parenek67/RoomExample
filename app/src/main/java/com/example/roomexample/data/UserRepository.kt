package com.example.roomexample.data

import androidx.lifecycle.LiveData

class UserRepository(val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllUsers()

    suspend fun insert(user: User){
        userDao.insert(user)
    }
}