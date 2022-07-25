package com.test.masschallenge.util

class HttpException(val statusCode: Int, override val message: String?,val reqBody : String? = null , val resBody : String? = null) : Exception()