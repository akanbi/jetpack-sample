package com.akanbi.jetpacksample.infrastructure.di

import androidx.room.Room
import com.akanbi.jetpacksample.infrastructure.dao.UserDAO
import com.akanbi.jetpacksample.infrastructure.database.UserRegisterDatabase
import org.koin.dsl.module

private const val BD_NAME = "user_register.db"

val moduleByApp = module {

    single<UserRegisterDatabase> {
        Room.databaseBuilder(
            get(),
            UserRegisterDatabase::class.java,
            BD_NAME
        ).build()
    }

    single<UserDAO> {
        get<UserRegisterDatabase>().userDao
    }
}