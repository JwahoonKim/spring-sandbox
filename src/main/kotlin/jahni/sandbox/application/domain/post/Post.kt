package jahni.sandbox.application.domain.post

import jahni.sandbox.application.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val authorId: Long,
    var title: String,
    var content: String,
    var isDeleted: Boolean = false,
) : BaseTimeEntity() {

    fun update(newTitle: String, newContent: String) {
        this.title = newTitle
        this.content = newContent
    }

}