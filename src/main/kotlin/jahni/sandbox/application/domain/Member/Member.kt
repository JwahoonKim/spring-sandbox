package jahni.sandbox.application.domain.Member

import jahni.sandbox.application.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val loginId: String,
    var password: String,
) : BaseTimeEntity()