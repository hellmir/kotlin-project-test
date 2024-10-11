package com.example.kotlinprojecttest.user.repository

import com.example.kotlinprojecttest.user.domain.Users

interface UserRepositoryCustom {

    fun findUserWithRole(email: String) : Users

}