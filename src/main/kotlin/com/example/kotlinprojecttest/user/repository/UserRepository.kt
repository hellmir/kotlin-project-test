package com.example.kotlinprojecttest.user.repository

import com.example.kotlinprojecttest.user.domain.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Long>, UserRepositoryCustom {
}