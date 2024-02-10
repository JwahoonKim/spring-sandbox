package jahni.sandbox.infra.database

import jahni.sandbox.application.domain.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long> {
}