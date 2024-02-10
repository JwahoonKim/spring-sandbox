package jahni.sandbox.application.domain.Member

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L,
    private val loginId: String,
    private var password: String,
) {

    fun getId(): Long {
        return this.id
    }

    fun getLoginId(): String {
        return this.loginId
    }

    fun getPassword(): String {
        return this.password
    }

}