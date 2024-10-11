package com.example.kotlinprojecttest.user.service

import com.example.kotlinprojecttest.user.dto.UserDto
import com.example.kotlinprojecttest.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("Username not found")
        }
        val findUser = userRepository.findUserWithRole(username)
        return UserDto(
        findUser.id,findUser.email,findUser.password,findUser.username,findUser.username,findUser.fullName,
            findUser.roles.map { it.role }.toMutableList()
        )
    }
}