package com.akanbi.jetpacksample.domain.validation.rules

import android.content.Context
import com.akanbi.jetpacksample.R
import com.akanbi.jetpacksample.domain.exception.UserException
import com.akanbi.jetpacksample.domain.model.User
import com.akanbi.jetpacksample.domain.validation.RuleValidation

class FieldIsBlankRuleValidation(private val context: Context) : RuleValidation<User> {

    override fun validate(model: User?) {
        if (model!!.name.isBlank())
            throw UserException(context.getString(R.string.nameFieldIsBlank))

        if (model.surname.isBlank())
            throw UserException(context.getString(R.string.surnameFieldIsBlank))
    }

}