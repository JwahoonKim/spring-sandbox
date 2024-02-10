package jahni.sandbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
class SandboxApplication

fun main(args: Array<String>) {
	runApplication<SandboxApplication>(*args)
}
