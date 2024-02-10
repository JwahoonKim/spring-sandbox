package jahni.sandbox.application.domain.Member

import jahni.sandbox.application.exception.JahniCustomException
import jahni.sandbox.infra.database.MemberJpaRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberJpaRepository
) {
    fun register(member: Member) {
        memberRepository.findByLoginId(member.loginId)
            ?.let { throw JahniCustomException("이미 존재하는 회원입니다.") }
        memberRepository.save(member)
    }

    fun getMember(memberId: Long, targetMemberId: Long): Member {
        if (memberId != targetMemberId) throw JahniCustomException("본인만 조회할 수 있습니다.")
        return memberRepository.findById(targetMemberId).orElseThrow { JahniCustomException("존재하지 않는 회원입니다.") }
    }

    fun update(newMember: Member) {
        memberRepository.save(newMember)
    }
}