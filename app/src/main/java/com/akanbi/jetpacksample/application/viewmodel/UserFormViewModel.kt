package com.akanbi.jetpacksample.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.repository.UserRepository

class UserFormViewModel(private val repository: UserRepository) : ViewModel() {

    fun save(user: User): LiveData<Void?> {
        return repository.save(user)
    }

}