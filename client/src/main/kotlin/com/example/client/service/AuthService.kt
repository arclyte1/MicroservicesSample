package com.example.client.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class AuthService {

    private val objectMapper = jacksonObjectMapper()

    @OptIn(ExperimentalEncodingApi::class)
    private fun checkTokenHasCinemaRole(authorization: String): Boolean {
        try {
            val token = authorization.split(" ")[1]
            val elements = token.split('.')
            if (elements.size == 3) {
                val payload = elements[1]
                val decoded = Base64.decode(payload).decodeToString()
                val authJwtPayload = objectMapper.readTree(decoded)
                return authJwtPayload["resource_access"]["cinema"]["roles"].any { it.textValue() == "access" }
            } else {
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun auth(authorization: String): AuthStatus {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(AUTH_URL)
            .addHeader("Authorization", authorization)
            .build()
        val response = client.newCall(request).execute()
        return if (response.code == 200) {
            if (checkTokenHasCinemaRole(authorization)) {
                AuthStatus.Authorized
            } else {
                AuthStatus.Other(403)
            }
        } else {
            AuthStatus.Other(response.code)
        }
    }

    companion object {
        private val AUTH_URL = System.getenv("AUTH_URL")
//        private val AUTH_URL = "http://localhost:8082/realms/master/protocol/openid-connect/userinfo"
    }
}

sealed class AuthStatus {
    data object Authorized : AuthStatus()
    data class Other(val statusCode: Int) : AuthStatus()
}