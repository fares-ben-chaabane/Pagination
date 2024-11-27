package com.amarchaud.data.models

sealed class ErrorApiDataModel : Throwable() {
    class ApiServerErrorWithCode(val responseCode: Int, val responseMessage: String) : ErrorApiDataModel()
    class ApiNullBody : ErrorApiDataModel()
    class ApiGenericServerError : ErrorApiDataModel()
    class SocketTimeOutError : ErrorApiDataModel()
    class NoInternetError : ErrorApiDataModel()
}
