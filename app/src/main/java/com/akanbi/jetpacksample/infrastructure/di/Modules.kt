package com.akanbi.jetpacksample.infrastructure.di

import androidx.room.Room
import com.akanbi.jetpacksample.application.viewmodel.UserFormViewModel
import com.akanbi.jetpacksample.application.viewmodel.UserListViewModel
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.repository.UserRepository
import com.akanbi.jetpacksample.domain.validation.rules.FieldIsBlankRuleValidation
import com.akanbi.jetpacksample.domain.validation.validators.UserFormValidator
import com.akanbi.jetpacksample.infrastructure.dao.UserDAO
import com.akanbi.jetpacksample.infrastructure.database.UserRegisterDatabase
import org.koin.android.viewmodel.dsl.viewModel
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

    single<UserRepository> {
        UserRepository(get())
    }

    single<FieldIsBlankRuleValidation> {
        FieldIsBlankRuleValidation(get())
    }

    single<UserFormValidator> {
        UserFormValidator()
    }

    viewModel<UserListViewModel> {
        UserListViewModel(get())
    }

    viewModel<UserFormViewModel> {
        UserFormViewModel(get())
    }
}