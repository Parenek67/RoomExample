package com.example.roomexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomexample.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    //синглтон
    companion object{
        @Volatile//поле становится видимым для других потоков
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            //synchronized блокирует доступ к коду, если
            //он уже используется другим потоком
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
