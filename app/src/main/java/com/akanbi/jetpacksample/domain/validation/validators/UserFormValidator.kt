package com.akanbi.jetpacksample.domain.validation.validators

import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.validation.RuleValidation
import com.akanbi.jetpacksample.domain.validation.Validator
import com.akanbi.jetpacksample.domain.validation.rules.FieldIsBlankRuleValidation
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserFormValidator : Validator<User>, KoinComponent {

    private val fieldIsBlankRule: FieldIsBlankRuleValidation by inject()

    override fun getRules(): List<RuleValidation<User>> = listOf(fieldIsBlankRule)

}