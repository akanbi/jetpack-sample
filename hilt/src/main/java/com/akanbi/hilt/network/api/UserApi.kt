package com.akanbi.hilt.network.api

import com.akanbi.hilt.network.model.UserList
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun list() : UserList

}