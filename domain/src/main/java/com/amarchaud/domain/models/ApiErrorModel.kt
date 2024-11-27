package com.amarchaud.domain.models

sealed class ErrorApiModel : Throwable() {
    class ApiServerErrorWithCode(val responseCode: Int, val responseMessage: String) : ErrorApiModel()
    class ApiNullBody : ErrorApiModel()
    class ApiGenericServerError : ErrorApiModel()
    class SocketTimeOutError : ErrorApiModel()
    class NoInternetError : ErrorApiModel()
    class GenericError : ErrorApiModel()
}
