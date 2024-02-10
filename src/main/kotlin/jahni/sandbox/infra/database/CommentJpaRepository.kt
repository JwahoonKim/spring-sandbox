package jahni.sandbox.infra.database

import jahni.sandbox.application.domain.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository : JpaRepository<Comment, Long> {
    fun findByPostId(postId: Long): List<Comment>
}