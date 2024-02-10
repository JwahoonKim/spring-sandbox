package jahni.sandbox.api.controller.comment.request

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.comment.CreateCommentCommand

data class CreateCommentRequest(
    val postId: Long,
    val content: String,
) {
    fun toCommand(member: Member): CreateCommentCommand {
        return CreateCommentCommand(
            authorId = member.id,
            postId = postId,
            content = content,
        )
    }
}
