package com.akanbi.jetpacksample.domain.validation.validators

import android.content.Context
import com.akanbi.jetpacksample.domain.exception.UserException
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.validation.rules.FieldIsBlankRuleValidation
import com.akanbi.jetpacksample.infrastructure.di.diModuleUnitTest
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.joda.time.LocalDateTime
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class UserFormValidatorTest : KoinTest {

    private val validator: UserFormValidator by inject()
    private val fieldIsBlankRuleValidation: FieldIsBlankRuleValidation by inject()
    @MockK
    private lateinit var context: Context

    @Before
    fun setUp() {
        init(this)
        stopKoin()
        startKoin {
            androidContext(context)
            modules(diModuleUnitTest)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldValidationSuccess() {
        val user = User(name = "User", surname = "Surname", birthDate = LocalDateTime.now())

        validator.validate(user)
    }

    @Test(expected = UserException::class)
    fun shouldThrowUserExceptionWhenNameIsBlank() {
        every { context.getString(any()) } returns "exceptionNameRequired"
        val user = User(name = "", surname = "Surname", birthDate = LocalDateTime.now())

        validator.validate(user)
    }

    @Test(expected = UserException::class)
    fun shouldThrowUserExceptionWhenSurnameIsBlank() {
        every { context.getString(any()) } returns "exceptionSurnameRequired"
        val user = User(name = "User", surname = "", birthDate = LocalDateTime.now())

        validator.validate(user)
    }
}