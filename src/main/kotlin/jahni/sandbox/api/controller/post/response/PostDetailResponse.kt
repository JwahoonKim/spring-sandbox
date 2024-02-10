package jahni.sandbox.api.controller.post.response

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.comment.Comment
import jahni.sandbox.application.domain.post.PostDetailOutput
import java.time.LocalDateTime

data class PostDetailResponse(
    val id: Long,
    val author: AuthorInfo,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val comments: List<CommentInfo>,
) {
    data class AuthorInfo(
        val id: Long,
        val loginId: String,
    ) {
        companion object {
            fun from(author: Member): AuthorInfo {
                return AuthorInfo(
                    id = author.id,
                    loginId = author.loginId,
                )
            }
        }
    }

    data class CommentInfo(
        val id: Long,
        val author: AuthorInfo,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) {
        companion object {
            fun of(comment: Comment, commentAuthors: Map<Long, Member>): CommentInfo {
                return CommentInfo(
                    id = comment.id,
                    author = AuthorInfo.from(commentAuthors[comment.authorId]!!),
                    content = comment.content,
                    createdAt = comment.createdAt,
                    updatedAt = comment.updatedAt,
                )
            }
        }
    }

    companion object {
        fun from(postDetailOutput: PostDetailOutput): PostDetailResponse {
            return PostDetailResponse(
                id = postDetailOutput.post.id,
                author = AuthorInfo.from(postDetailOutput.postAuthor),
                title = postDetailOutput.post.title,
                content = postDetailOutput.post.content,
                createdAt = postDetailOutput.post.createdAt,
                updatedAt = postDetailOutput.post.updatedAt,
                comments = postDetailOutput.comments.map { CommentInfo.of(it, postDetailOutput.commentAuthors) }
            )
        }
    }
}
