package com.example.kotlinprojecttest.util

import com.example.kotlinprojecttest.user.exception.JwtCustomException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8
import java.time.ZonedDateTime
import java.util.*
import javax.crypto.SecretKey


    private var SECRETE_KEY: String = "2fe19d2b1073ef12a8ae07f70b9696a4b15800d364d3f8dc1012449a7bd910a5093e6fcc2f854591c1564feb579016a9e71e8a6bc8267e887cf70a1152c5fe67"

    private val key: SecretKey = Keys.hmacShaKeyFor(SECRETE_KEY.toByteArray(UTF_8))

    fun generateToken(valueMap: MutableMap<String, Any?>, min: Long): String {
        return Jwts.builder()
            .setHeader(mapOf("typ" to "JWT"))
            .setClaims(valueMap)
            .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
            .signWith(key)
            .compact()
    }

    fun validateJwtToken(token: String): MutableMap<String, Any>? {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body ?: null
        } catch (malformedJwtException: MalformedJwtException) {
            throw JwtCustomException("MalFormed")
        } catch (expiredJwtException: ExpiredJwtException) {
            throw JwtCustomException("Expired")
        } catch (invalidClaimException: InvalidClaimException) {
            throw JwtCustomException("Invalid")
        } catch (jwtException: JwtException) {
            throw JwtCustomException("JWTError")
        } catch (e: Exception) {
            throw JwtCustomException("Exception")
        }
    }

