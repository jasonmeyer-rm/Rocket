package com.app.rocket.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResult<T> = ApiResult(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResult<T> =
            ApiResult(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiResult<T> = ApiResult(status = Status.LOADING, data = data, message = null)
    }
}