package jahni.sandbox.api.controller.member.request

import jahni.sandbox.application.domain.Member.LoginCommand
import jakarta.servlet.http.HttpServletRequest

class LoginMemberRequest(
    val loginId: String,
    val password: String,
) {
    fun toCommand(httpRequest: HttpServletRequest): LoginCommand {
        return LoginCommand(
            loginId = loginId,
            password = password,
            httpRequest = httpRequest,
        )
    }
}