package com.akanbi.jetpacksample.infrastructure.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.akanbi.jetpacksample.infrastructure.database.UserRegisterDatabase
import org.koin.dsl.module

val modulesAppTest = module {
    single { ApplicationProvider.getApplicationContext<Context>() as Context }

    single<UserRegisterDatabase> {
        Room.inMemoryDatabaseBuilder(
            get(),
            UserRegisterDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}