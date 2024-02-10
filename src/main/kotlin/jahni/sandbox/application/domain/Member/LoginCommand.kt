package jahni.sandbox.application.domain.Member

import jakarta.servlet.http.HttpServletRequest

class LoginCommand(
    val loginId: String,
    val password: String,
    val httpRequest: HttpServletRequest,
)