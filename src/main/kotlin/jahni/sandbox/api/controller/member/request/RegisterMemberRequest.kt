package jahni.sandbox.api.controller.member.request

import jahni.sandbox.application.domain.Member.Member

data class RegisterMemberRequest(
    val loginId: String,
    val password: String,
) {
    fun toDomain(): Member {
        return Member(
            loginId = loginId,
            password = password,
        )
    }
}
