package com.akanbi.hilt.domain

import com.akanbi.commonkotlin.extensions.ProviderContext
import com.akanbi.commonkotlin.extensions.ResultType
import com.akanbi.commonkotlin.extensions.handleResultType
import com.akanbi.commonkotlin.model.ResponseError
import com.akanbi.hilt.network.model.User
import com.akanbi.hilt.network.model.UserList
import com.akanbi.hilt.repository.UserRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val context: ProviderContext
) {

    suspend fun execute(
        onSuccess: (List<User>) -> Unit,
        onError: (ResponseError) -> Unit) {

        val listByRepository: ResultType<UserList>
        withContext(context.io) {
            listByRepository = userRepository.list()
        }
        listByRepository.handleResultType(
            success = {
                onSuccess(buildUserList(it))
            },
            error = {
                onError(it)
            }
        )
    }

    private fun buildUserList(userListResult: UserList) =
        userListResult.userList

}