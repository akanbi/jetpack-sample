package com.akanbi.commonkotlin.model

class ResponseError(
    override val cause: Throwable = Throwable(),
    val code: Int = 0
): Exception("", cause)