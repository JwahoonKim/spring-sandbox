package jahni.sandbox.api.controller.post.request

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.post.UpdatePostCommand

data class UpdatePostRequest(
    val postId: Long,
    val newTitle: String,
    val newContent: String,
) {
    fun toCommand(member: Member): UpdatePostCommand {
        return UpdatePostCommand(
            memberId = member.id,
            postId = postId,
            newTitle = newTitle,
            newContent = newContent,
        )
    }
}
