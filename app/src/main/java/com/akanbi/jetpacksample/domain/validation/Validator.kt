package com.akanbi.jetpacksample.domain.validation


interface Validator<T> {

    fun validate(entity: T?) {
        for (ruleValidation in getRules()) {
            ruleValidation.validate(entity)
        }
    }

    fun getRules(): List<RuleValidation<T>>

}