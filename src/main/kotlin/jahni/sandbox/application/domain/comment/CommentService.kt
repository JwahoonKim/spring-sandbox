package jahni.sandbox.application.domain.comment

import jahni.sandbox.application.exception.JahniCustomException
import jahni.sandbox.infra.database.CommentJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentJpaRepository,
) {
    fun createComment(command: CreateCommentCommand) {
        val comment = command.toEntity()
        commentRepository.save(comment)
    }

    @Transactional
    fun deleteComment(memberId: Long, commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw JahniCustomException("존재하지 않는 댓글입니다.")
        if (memberId != comment.authorId)
            throw JahniCustomException("댓글 작성자만 삭제할 수 있습니다.")
        commentRepository.delete(comment)
    }

    @Transactional
    fun updateComment(command: UpdateCommentCommand) {
        val (updaterId, commentId, newContent) = command
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw JahniCustomException("존재하지 않는 댓글입니다.")
        if (updaterId != comment.authorId)
            throw JahniCustomException("댓글 작성자만 수정할 수 있습니다.")
        comment.updateContent(newContent)
    }
}
