package com.example.roomexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstname: String,
    val lastname: String,
    val age: Int
)