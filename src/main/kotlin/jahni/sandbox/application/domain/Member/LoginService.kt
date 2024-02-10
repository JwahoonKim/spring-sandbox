package jahni.sandbox.application.domain.Member

import jahni.sandbox.application.exception.JahniCustomException
import jahni.sandbox.infra.database.member.MemberJpaRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val memberJpaRepository: MemberJpaRepository,
) {
    /**
     * JSESSIONID를 통해 회원 정보를 가져오는 방식은 아래와 같은 구조로 이해하면 된다.
     * getSession -> SessionStore[JSESSIONID] -> Session
     * Session -> Session[LOGIN_MEMBER] -> Member 정보
     * */
    fun login(command: LoginCommand) {
        val member = memberJpaRepository.findByLoginId(command.loginId)
            ?: throw JahniCustomException("존재하지 않는 회원입니다.")
        checkPassword(member, command)
        val session = command.httpRequest.getSession(true) // 세션이 있다면 기존것을, 없다면 신규 생성
        session.setAttribute("LOGIN_MEMBER", member) // 세션에 로그인 회원 정보 저장 (Map 형태로 저장됨)
    }

    private fun checkPassword(member: Member, command: LoginCommand) {
        if (member.getPassword() != command.password)
            throw JahniCustomException("비밀번호가 일치하지 않습니다.")
    }
}
