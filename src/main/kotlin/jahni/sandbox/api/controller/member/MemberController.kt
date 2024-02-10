package jahni.sandbox.api.controller.member

import jahni.sandbox.api.configuration.LoginUser
import jahni.sandbox.api.controller.member.request.LoginMemberRequest
import jahni.sandbox.api.controller.member.request.RegisterMemberRequest
import jahni.sandbox.api.controller.member.request.UpdatePasswordRequest
import jahni.sandbox.api.controller.member.response.MemberResponse
import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.domain.Member.*
import jahni.sandbox.application.exception.JahniCustomException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService,
    private val loginService: LoginService,
) {

    @PostMapping("/member")
    fun register(@RequestBody request: RegisterMemberRequest): ApiResponse<String> {
        memberService.register(request.toDomain())
        return ApiResponse.success("회원가입이 완료되었습니다.")
    }

    @PostMapping("/member/login")
    fun login(
        httpRequest: HttpServletRequest,
        @RequestBody request: LoginMemberRequest,
    ): ApiResponse<String> {
        loginService.login(request.toCommand(httpRequest))
        return ApiResponse.success("로그인이 완료되었습니다.")
    }

    @GetMapping("/member/{id}")
    fun getMember(
        @PathVariable id: Long,
        @LoginUser member: Member,
    ): ApiResponse<MemberResponse> {
        if (member.getId() != id) throw JahniCustomException("본인만 조회할 수 있습니다.")
        val findMember = memberService.getMember(id)
        val response = MemberResponse(
            id = findMember.getId(),
            loginId = findMember.getLoginId(),
            password = findMember.getPassword(),
        )
        return ApiResponse.success(response)
    }

    @PatchMapping("/member/password")
    fun updatePassword(
        @LoginUser member: Member,
        @RequestBody request: UpdatePasswordRequest,
    ): ApiResponse<String> {
        memberService.update(request.toDomain(member))
        return ApiResponse.success("비밀번호가 변경되었습니다.")
    }
}