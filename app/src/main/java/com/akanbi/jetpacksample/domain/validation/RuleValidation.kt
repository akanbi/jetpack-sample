package com.akanbi.jetpacksample.domain.validation

interface RuleValidation<T> {

    fun validate(model: T?)

}