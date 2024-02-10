package jahni.sandbox.application.domain.comment

data class CreateCommentCommand(
    val authorId: Long,
    val postId: Long,
    val content: String,
) {
    fun toEntity(): Comment {
        return Comment(
            authorId = authorId,
            postId = postId,
            content = content,
        )
    }
}
