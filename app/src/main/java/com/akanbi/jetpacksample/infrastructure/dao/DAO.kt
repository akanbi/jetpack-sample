package com.akanbi.jetpacksample.infrastructure.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface DAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: T)

    @Delete
    fun delete(model: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: T)

}