package com.akanbi.jetpacksample.domain.repository

import androidx.lifecycle.MutableLiveData
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.infrastructure.dao.UserDAO
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    @InjectMockKs
    private lateinit var repository: UserRepository
    @MockK
    private lateinit var dao: UserDAO

    @Before
    fun setUp() {
        init(this)
    }

    @Test
    fun shouldSaveUser() {
        val user = User(name = "User", surname = "User Surname", birthDate = LocalDateTime.now())

        repository.save(user)

        verify { dao.insert(user) }
    }

    @Test
    fun shouldEditUser() {
        val user = User(name = "Other User", surname = "User Surname", birthDate = LocalDateTime.now())

        repository.edit(user)

        verify { dao.update(user) }
    }

    @Test
    fun shouldDeleteUser() {
        val user = User(name = "User", surname = "User Surname", birthDate = LocalDateTime.now())

        repository.delete(user)

        verify { dao.delete(user) }
    }

    @Test
    fun shouldListAllUsers() {
        val userOne = User(name = "User One", surname = "User Surname One", birthDate = LocalDateTime.now())
        val userTwo = User(name = "User Two", surname = "User Surname Two", birthDate = LocalDateTime.now())
        val userThree = User(name = "User Three", surname = "User Surname Three", birthDate = LocalDateTime.now())
        val userFour = User(name = "User Four", surname = "User Surname Four", birthDate = LocalDateTime.now())
        val userFive = User(name = "User Five", surname = "User Surname Five", birthDate = LocalDateTime.now())
        every { dao.listAll() } returns MutableLiveData(arrayListOf(userOne, userTwo, userThree, userFour, userFive))

        val listResult = repository.listAll()

        assertEquals(5, listResult.value!!.size)
        assertEquals(userOne.name, listResult.value!![0].name)
        assertEquals(userTwo.name, listResult.value!![1].name)
        assertEquals(userThree.name, listResult.value!![2].name)
        assertEquals(userFour.name, listResult.value!![3].name)
        assertEquals(userFive.name, listResult.value!![4].name)
    }

}