package jahni.sandbox.api.controller.member

import jahni.sandbox.api.controller.member.request.LoginMemberRequest
import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.domain.Member.LoginService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginService: LoginService,
) {
    @PostMapping("/login")
    fun login(
        httpRequest: HttpServletRequest,
        @RequestBody request: LoginMemberRequest,
    ): ApiResponse<String> {
        loginService.login(request.toCommand(httpRequest))
        return ApiResponse.success("로그인이 완료되었습니다.")
    }
}