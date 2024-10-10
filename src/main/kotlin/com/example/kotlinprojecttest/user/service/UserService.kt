package com.example.kotlinprojecttest.user.service

import com.example.kotlinprojecttest.user.dto.JoinRequest

interface UserService {
    fun save(joinRequest: JoinRequest)
}