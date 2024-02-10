package jahni.sandbox.application.domain.post

data class CreatePostCommand(
    private val authorId: Long,
    private val title: String,
    private val content: String,
) {
    fun toEntity(): Post {
        return Post(
            authorId = authorId,
            title = title,
            content = content,
        )
    }
}
