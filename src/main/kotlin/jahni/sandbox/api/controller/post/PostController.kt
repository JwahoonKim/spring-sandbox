package jahni.sandbox.api.controller.post

import jahni.sandbox.api.configuration.LoginMember
import jahni.sandbox.api.controller.post.request.CreatePostRequest
import jahni.sandbox.api.controller.post.request.UpdatePostRequest
import jahni.sandbox.api.controller.post.response.PostDetailResponse
import jahni.sandbox.api.controller.post.response.PostResponse
import jahni.sandbox.api.util.ApiResponse
import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.domain.post.PostService
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService
) {

    // 글 등록
    @PostMapping("/post")
    fun createPost(
        @LoginMember member: Member,
        @RequestBody request: CreatePostRequest,
    ) : ApiResponse<String> {
        postService.createPost(request.toCommand(member))
        return ApiResponse.success("글이 등록되었습니다.")
    }

    // 글 조회 + 댓글까지
    @GetMapping("/post/{postId}")
    fun getPost(
        @PathVariable postId: Long,
    ): ApiResponse<PostDetailResponse> {
        val postDetailOutput = postService.getPost(postId)
        val response = PostDetailResponse.from(postDetailOutput)
        return ApiResponse.success(response)
    }

    // 글 수정
    @PutMapping("/post")
    fun updatePost(
        @LoginMember member: Member,
        @RequestBody request: UpdatePostRequest,
    ): ApiResponse<String> {
        val command = request.toCommand(member)
        postService.updatePost(command)
        return ApiResponse.success("글이 수정되었습니다.")
    }

    // 글 삭제
    @DeleteMapping("/post/{postId}")
    fun deletePost(
        @LoginMember member: Member,
        @PathVariable postId: Long,
    ): ApiResponse<String> {
        postService.deletePost(member, postId)
        return ApiResponse.success("글이 삭제되었습니다.")
    }

    // 글 전부 조회 + 댓글 제외
    // 페이징 처리
    @GetMapping("/posts")
    fun getPosts(): ApiResponse<List<PostResponse>> {
        val posts = postService.getAllPost()
        val response = posts.map { PostResponse.from(it) }
        return ApiResponse.success(response)
    }

}