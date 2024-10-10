package com.example.kotlinprojecttest.user.dto

import com.example.kotlinprojecttest.user.domain.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class UserDto(
    private val id: Any?,
    private val email: String,
    private val password: String,
    private val userId: String,
    private val username: String,
    private val fullName: String,
    private var roles: MutableList<String?> = mutableListOf()
): User(
    email,password,roles.map{SimpleGrantedAuthority("ROLE_${it}")}
) {

    fun getClaims(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to id,
            "email" to email,
            "userId" to userId,
            "username" to username,
            "fullName" to fullName,
            "roles" to roles
        )
    }
}