package com.example.kotlinprojecttest.user.service

import com.example.kotlinprojecttest.user.domain.Users
import com.example.kotlinprojecttest.user.dto.JoinRequest
import com.example.kotlinprojecttest.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
    ) : UserService {
    @Transactional
    override fun save(joinRequest: JoinRequest) {
        val email = joinRequest.email
        val password = passwordEncoder.encode(joinRequest.password)
        val fullName = joinRequest.fullName
        val username = joinRequest.username
        val createUser = Users.createUser(email, username,password, fullName)
        userRepository.save(createUser)
    }
}