package com.akanbi.jetpacksample.application.viewmodel

import androidx.lifecycle.MutableLiveData
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.repository.UserRepository
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.joda.time.LocalDateTime
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserListViewModelTest {
    @InjectMockKs
    private lateinit var viewModel: UserListViewModel
    @MockK
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        init(this)
    }

    @Test
    fun shouldListAllUsers() {
        every { repository.listAll() } returns MutableLiveData(arrayListOf(
            User(name = "User One", surname = "User Surname One", birthDate = LocalDateTime.now()),
            User(name = "User Two", surname = "User Surname Two", birthDate = LocalDateTime.now())
        ))

        val listResult = viewModel.list()

        assertNotNull(listResult)
        assertEquals("User One", listResult.value?.get(0)?.name)
        assertEquals("User Two", listResult.value?.get(1)?.name)
    }

    @Test
    fun shouldRemoveUser() {
        val user = User(name = "User", surname = "User Surname", birthDate = LocalDateTime.now())
        every { repository.delete(user) } returns MutableLiveData(null)

        val result = viewModel.remove(user)

        assertNull(result.value)
    }

}