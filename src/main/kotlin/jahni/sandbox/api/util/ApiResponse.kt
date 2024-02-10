package jahni.sandbox.api.util

import jahni.sandbox.application.exception.ErrorMessage

class ApiResponse<T> {

    val resultType: ResultType
    val result: T?
    val errorMessage: ErrorMessage?

    companion object {
        fun <T> success(result: T): ApiResponse<T> {
            return ApiResponse(result)
        }

        fun <T> fail(errorMessage: ErrorMessage): ApiResponse<T> {
            return ApiResponse(errorMessage)
        }
    }

    private constructor(result: T) {
        this.resultType = ResultType.SUCCESS
        this.result = result
        this.errorMessage = null
    }

    private constructor(errorMessage: ErrorMessage) {
        this.resultType = ResultType.FAIL
        this.result = null
        this.errorMessage = errorMessage
    }
}