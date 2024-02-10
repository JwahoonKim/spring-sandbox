package jahni.sandbox.infra.database

import jahni.sandbox.application.domain.Member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
    fun findByIdIn(ids: List<Long>): List<Member>
}