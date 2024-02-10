package jahni.sandbox.application.domain.comment

data class UpdateCommentCommand(
    val updaterId: Long,
    val commentId: Long,
    val newContent: String
)
