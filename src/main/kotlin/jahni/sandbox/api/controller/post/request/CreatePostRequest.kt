package jahni.sandbox.api.controller.post.request

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.post.CreatePostCommand

data class CreatePostRequest(
    val title: String,
    val content: String,
) {
    fun toCommand(member: Member): CreatePostCommand {
        return CreatePostCommand(
            authorId = member.id,
            title = title,
            content = content,
        )
    }
}
