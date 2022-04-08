package com.example.roomexample.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(context: Context): ViewModel() {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(context).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun insertUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun getAllData() = readAllData

}

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}