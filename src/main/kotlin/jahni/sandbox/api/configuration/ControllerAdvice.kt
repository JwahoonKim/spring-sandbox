package jahni.sandbox.api.configuration

import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.exception.ErrorMessage
import jahni.sandbox.application.exception.JahniCustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse<String> {
        val errorMessage = ErrorMessage("Internal Server Error")
        // TODO: 슬랙에 에러 로그 전송
        return ApiResponse.fail(errorMessage)
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JahniCustomException::class)
    fun handleException(e: JahniCustomException): ApiResponse<String> {
        return ApiResponse.fail(ErrorMessage(e.message ?: "알 수 없는 에러입니다."))
    }
}