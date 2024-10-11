package com.example.kotlinprojecttest.security.handler

import com.example.kotlinprojecttest.user.dto.UserDto
import com.example.kotlinprojecttest.util.generateToken
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class LoginSuccessHandler: AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val objectMapper = ObjectMapper()
        val user: UserDto = authentication?.principal as UserDto
        val claims = user.getClaims()
        val accessToken = generateToken(claims, 10L)
        val refreshToken = generateToken(claims, 60 * 24)
        claims["access_token"] = accessToken
        claims["refresh_token"] = refreshToken
        response?.contentType = "application/json"
        response?.writer?.write(objectMapper.writeValueAsString(claims))
    }
}