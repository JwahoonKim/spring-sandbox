package jahni.sandbox.application.domain.post

data class UpdatePostCommand(
    val memberId: Long,
    val postId: Long,
    val newTitle: String,
    val newContent: String,
)
