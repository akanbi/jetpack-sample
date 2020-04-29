package com.akanbi.jetpacksample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val surname: String,
    val birthDate: Date
) {
}