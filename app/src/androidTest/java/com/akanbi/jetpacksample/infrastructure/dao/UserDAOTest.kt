package com.akanbi.jetpacksample.infrastructure.dao

import androidx.lifecycle.LiveData
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.infrastructure.database.UserRegisterDatabase
import com.akanbi.jetpacksample.infrastructure.di.modulesAppTest
import kotlinx.coroutines.*
import org.joda.time.LocalDateTime
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import org.koin.test.KoinTest

class UserDAOTest : KoinTest {

    private lateinit var dao: UserDAO

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(modulesAppTest)
        }
        dao = get<UserRegisterDatabase>().userDao
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun shouldInsertUser() {
        val user = User(name = "Muhammad", surname = "Ali", birthDate = LocalDateTime.now())

        dao.insert(user)

        val result = runCoroutineForGetResultOfLiveData(dao.listAll())

        if (result.first.isCompleted) {
            Assert.assertEquals(1, result.second?.size)
            Assert.assertEquals("Muhammad", result.second?.get(0)?.name)
        }
    }

    @Test
    fun shouldListAllUser() {
        val userOne = User(name = "Muhammad", surname = "Ali", birthDate = LocalDateTime.now())
        val userTwo = User(name = "Mike", surname = "Tyson", birthDate = LocalDateTime.now())
        val userThree = User(name = "Michael", surname = "Jordan", birthDate = LocalDateTime.now())
        val userFour = User(name = "Lebron", surname = "James", birthDate = LocalDateTime.now())
        val userFive = User(name = "Ronaldo", surname = "Nazário", birthDate = LocalDateTime.now())

        dao.insert(userOne)
        dao.insert(userTwo)
        dao.insert(userThree)
        dao.insert(userFour)
        dao.insert(userFive)

        val result = runCoroutineForGetResultOfLiveData(dao.listAll())

        if (result.first.isCompleted) {
            Assert.assertEquals(5, result.second?.size)
            Assert.assertEquals("Muhammad", result.second?.get(0)?.name)
            Assert.assertEquals("Mike", result.second?.get(1)?.name)
            Assert.assertEquals("Michael", result.second?.get(2)?.name)
            Assert.assertEquals("Lebron", result.second?.get(3)?.name)
            Assert.assertEquals("Ronaldo", result.second?.get(4)?.name)
        }
    }

    @Test
    fun shouldDeleteMoneyTransaction() {
        val userOne = User(name = "Muhammad", surname = "Ali", birthDate = LocalDateTime.now())
        val userTwo = User(name = "Mike", surname = "Tyson", birthDate = LocalDateTime.now())
        val userThree = User(name = "Michael", surname = "Jordan", birthDate = LocalDateTime.now())

        dao.insert(userOne)
        dao.insert(userTwo)
        dao.insert(userThree)

        dao.delete(userTwo)

        val result = runCoroutineForGetResultOfLiveData(dao.listAll())

        if (result.first.isCompleted) {
            Assert.assertEquals(2, result.second?.size)
            Assert.assertEquals(userOne.name, result.second?.get(0)?.name)
            Assert.assertEquals(userThree.name, result.second?.get(1)?.name)
        }
    }

    @Test
    fun shouldUpdateMoneyTransaction() {
        val userInserted = User(name = "Bill", surname = "Gates", birthDate = LocalDateTime.now())
        val userUpdated = User(name = "Steve", surname = "Jobs", birthDate = LocalDateTime.now())

        dao.insert(userInserted)

        dao.update(userUpdated)

        val result = runCoroutineForGetResultOfLiveData(dao.listAll())

        if (result.first.isCompleted) {
            Assert.assertEquals(1, result.second?.size)
            Assert.assertEquals(userUpdated.name, result.second?.get(0)?.name)
        }
    }

    private fun runCoroutineForGetResultOfLiveData(runOnCoroutine: LiveData<List<User>>): Pair<Job, List<User>?> {
        var resultList: List<User>? = null
        val job = CoroutineScope(Dispatchers.Main).launch {
            runOnCoroutine.observeForever {
                resultList = it
            }
            delay(2000)
        }
        return Pair(job, resultList)
    }

}