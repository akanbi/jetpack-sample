package com.akanbi.jetpacksample.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.repository.UserRepository

class UserListViewModel(private val repository: UserRepository) : ViewModel() {

    fun list(): LiveData<List<User>> {
        return repository.listAll()
    }

    fun remove(user: User): LiveData<Void?> {
        return repository.delete(user)
    }

}