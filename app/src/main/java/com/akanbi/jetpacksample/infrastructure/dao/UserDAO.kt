package com.akanbi.jetpacksample.infrastructure.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.akanbi.jetpacksample.domain.model.User

@Dao
interface UserDAO : DAO<User> {

    @Query("SELECT * FROM User ORDER BY id DESC")
    fun listAll(): LiveData<List<User>>

}