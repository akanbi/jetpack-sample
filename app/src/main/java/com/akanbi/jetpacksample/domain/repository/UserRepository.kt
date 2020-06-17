package com.akanbi.jetpacksample.domain.repository

import androidx.lifecycle.LiveData
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.infrastructure.dao.UserDAO

class UserRepository(private val userDAO: UserDAO) : Repository<User>(userDAO) {

    fun listAll(): LiveData<List<User>> {
        return userDAO.listAll()
    }

}