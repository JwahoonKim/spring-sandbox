package jahni.sandbox.api.controller.member

import jahni.sandbox.api.configuration.LoginMember
import jahni.sandbox.api.controller.member.request.RegisterMemberRequest
import jahni.sandbox.api.controller.member.request.UpdatePasswordRequest
import jahni.sandbox.api.controller.member.response.MemberResponse
import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.Member.MemberService
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/member")
    fun register(@RequestBody request: RegisterMemberRequest): ApiResponse<String> {
        memberService.register(request.toDomain())
        return ApiResponse.success("회원가입이 완료되었습니다.")
    }

    @GetMapping("/member/{id}")
    fun getMember(
        @PathVariable id: Long,
        @LoginMember member: Member,
    ): ApiResponse<MemberResponse> {
        val findMember = memberService.getMember(member.id, id)
        val response = MemberResponse(
            id = findMember.id,
            loginId = findMember.loginId,
            password = findMember.password,
        )
        return ApiResponse.success(response)
    }

    @PatchMapping("/member/password")
    fun updatePassword(
        @LoginMember member: Member,
        @RequestBody request: UpdatePasswordRequest,
    ): ApiResponse<String> {
        memberService.update(request.toDomain(member))
        return ApiResponse.success("비밀번호가 변경되었습니다.")
    }
}