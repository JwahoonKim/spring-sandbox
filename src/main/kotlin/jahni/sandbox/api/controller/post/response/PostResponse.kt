package jahni.sandbox.api.controller.post.response

import jahni.sandbox.application.domain.post.Post
import java.time.LocalDateTime

data class PostResponse(
    val id: Long,
    val authorId: Long,
    // 작성자 정보 추가하기
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(post: Post): PostResponse {
            return PostResponse(
                id = post.id,
                authorId = post.authorId,
                title = post.title,
                content = post.content,
                createdAt = post.createdAt,
                updatedAt = post.updatedAt,
            )
        }
    }
}