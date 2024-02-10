package jahni.sandbox.application.domain.post

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.comment.Comment

data class PostDetailOutput(
    val post: Post,
    val postAuthor: Member,
    val comments: List<Comment>,
    val commentAuthors: Map<Long, Member>,
)