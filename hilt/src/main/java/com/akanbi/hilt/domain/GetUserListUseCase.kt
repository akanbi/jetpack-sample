package com.akanbi.hilt.domain

import com.akanbi.hilt.repository.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute() {
        val list = userRepository.list()
    }

}