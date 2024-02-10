package jahni.sandbox.application.domain.post

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.exception.JahniCustomException
import jahni.sandbox.infra.database.CommentJpaRepository
import jahni.sandbox.infra.database.MemberJpaRepository
import jahni.sandbox.infra.database.PostJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val memberRepository: MemberJpaRepository,
    private val postRepository: PostJpaRepository,
    private val commentRepository: CommentJpaRepository,
) {
    fun createPost(command: CreatePostCommand) {
        postRepository.save(command.toEntity())
    }

    @Transactional(readOnly = true)
    fun getPost(postId: Long): PostDetailOutput {
        val post = postRepository.findByIdOrNull(postId) ?: throw JahniCustomException("존재하지 않는 글입니다.")
        val comments = commentRepository.findByPostId(post.id)
        val postAuthor = memberRepository.findByIdOrNull(post.authorId) ?: throw JahniCustomException("존재하지 않는 작성자입니다.")
        val commentAuthorIds = comments.map { it.authorId }
        val commentAuthors = memberRepository.findByIdIn(commentAuthorIds)
        val commentAuthorMap = commentAuthors.associateBy { it.id }

        return PostDetailOutput(post, postAuthor, comments, commentAuthorMap)
    }

    @Transactional
    fun updatePost(command: UpdatePostCommand) {
        val post = postRepository.findByIdOrNull(command.postId) ?: throw JahniCustomException("존재하지 않는 글입니다.")
        if (command.memberId != post.authorId) {
            throw JahniCustomException("글 작성자만 수정할 수 있습니다.")
        }
        post.update(command.newTitle, command.newContent)
    }

    fun getAllPost(): List<Post> {
        return postRepository.findAll()
    }

    @Transactional
    fun deletePost(member: Member, postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw JahniCustomException("존재하지 않는 글입니다.")
        if (member.id != post.authorId) {
            throw JahniCustomException("글 작성자만 삭제할 수 있습니다.")
        }
        postRepository.delete(post)
    }
}
