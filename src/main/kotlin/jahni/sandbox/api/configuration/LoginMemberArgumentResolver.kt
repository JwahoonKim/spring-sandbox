package jahni.sandbox.api.configuration

import jahni.sandbox.application.domain.Member.Member
import jahni.sandbox.application.exception.JahniCustomException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class LoginMemberArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasLoginAnnotation = parameter.hasParameterAnnotation(LoginUser::class.java)
        val hasMemberType = Member::class.java.isAssignableFrom(parameter.parameterType)
        return hasLoginAnnotation && hasMemberType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val request = webRequest.nativeRequest as HttpServletRequest
        return request.getSession(false)?.getAttribute("LOGIN_MEMBER")
            ?: throw JahniCustomException("로그인이 필요합니다.")
    }
}