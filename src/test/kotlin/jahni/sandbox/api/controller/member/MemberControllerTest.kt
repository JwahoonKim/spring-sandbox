package jahni.sandbox.api.controller.member

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun 회원가입() {
        mockMvc.post("/member") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "loginId": "test",
                    "password": "test"
                }
            """
        }.andExpectAll {
            status { isOk() }
            jsonPath("$.result") { value("회원가입이 완료되었습니다.") }
        }
    }
}