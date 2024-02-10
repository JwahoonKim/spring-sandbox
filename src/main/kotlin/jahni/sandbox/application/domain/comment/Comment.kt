package jahni.sandbox.application.domain.comment

import jahni.sandbox.application.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val authorId: Long,
    val postId: Long,
    var content: String,
) : BaseTimeEntity() {

    fun updateContent(newContent: String) {
        this.content = newContent
    }

}