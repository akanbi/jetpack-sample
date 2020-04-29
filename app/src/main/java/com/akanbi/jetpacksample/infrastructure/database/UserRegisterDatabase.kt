package com.akanbi.jetpacksample.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.infrastructure.dao.UserDAO
import com.akanbi.jetpacksample.infrastructure.database.converters.DateConverter

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class UserRegisterDatabase : RoomDatabase() {

    abstract val userDao: UserDAO

}