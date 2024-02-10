package jahni.sandbox.api.controller.member.request

import jahni.sandbox.application.domain.Member.Member

data class UpdatePasswordRequest(
    val newPassword: String,
) {
    fun toDomain(oldMember: Member): Member {
        return Member(
            id = oldMember.id,
            loginId = oldMember.loginId,
            password = newPassword,
        )
    }
}
