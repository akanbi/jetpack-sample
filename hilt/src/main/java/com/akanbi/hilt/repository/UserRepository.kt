package com.akanbi.hilt.repository

import com.akanbi.commonkotlin.extensions.safeApiCall
import com.akanbi.hilt.network.api.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
){

    suspend fun list() = safeApiCall {
        userApi.list()
    }
}