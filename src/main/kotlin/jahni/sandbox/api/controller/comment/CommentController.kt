package jahni.sandbox.api.controller.comment

import jahni.sandbox.api.configuration.LoginMember
import jahni.sandbox.api.controller.comment.request.CreateCommentRequest
import jahni.sandbox.api.controller.comment.request.UpdateCommentRequest
import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.comment.CommentService
import jahni.sandbox.application.domain.comment.UpdateCommentCommand
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService,
) {
    // 댓글 등록
    @PostMapping("/comment")
    fun createComment(
        @LoginMember member: Member,
        @RequestBody request: CreateCommentRequest
    ) : ApiResponse<String> {
        commentService.createComment(request.toCommand(member))
        return ApiResponse.success("댓글이 등록되었습니다.")
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    fun deleteComment(
        @LoginMember member: Member,
        @PathVariable commentId: Long
    ) : ApiResponse<String> {
        commentService.deleteComment(member.id, commentId)
        return ApiResponse.success("댓글이 삭제되었습니다.")
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    fun updateComment(
        @LoginMember member: Member,
        @PathVariable commentId: Long,
        @RequestBody request: UpdateCommentRequest
    ) : ApiResponse<String> {
        val command = UpdateCommentCommand(member.id, commentId, request.content)
        commentService.updateComment(command)
        return ApiResponse.success("댓글이 수정되었습니다.")
    }
}