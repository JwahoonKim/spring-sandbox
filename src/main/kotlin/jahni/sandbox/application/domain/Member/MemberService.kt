package jahni.sandbox.application.domain.Member

import jahni.sandbox.application.exception.JahniCustomException
import jahni.sandbox.infra.database.member.MemberJpaRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberJpaRepository
) {
    fun register(member: Member) {
        memberRepository.findByLoginId(member.getLoginId())
            ?.let { throw JahniCustomException("이미 존재하는 회원입니다.") }
        memberRepository.save(member)
    }

    fun getMember(id: Long): Member {
        return memberRepository.findById(id).orElseThrow { JahniCustomException("존재하지 않는 회원입니다.") }
    }

    fun update(newMember: Member) {
        memberRepository.save(newMember)
    }
}