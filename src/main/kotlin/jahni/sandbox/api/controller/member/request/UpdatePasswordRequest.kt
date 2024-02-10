package jahni.sandbox.api.controller.member.request

import jahni.sandbox.application.domain.Member.Member

class UpdatePasswordRequest(
    val newPassword: String,
) {
    fun toDomain(oldMember: Member): Member {
        return Member(
            id = oldMember.getId(),
            loginId = oldMember.getLoginId(),
            password = newPassword,
        )
    }
}
