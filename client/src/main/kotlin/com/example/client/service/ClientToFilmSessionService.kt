package com.example.client.service

import com.example.client.model.ClientToFilmSession
import com.example.client.repository.ClientToFilmSessionRepository
import org.springframework.stereotype.Service

@Service
class ClientToFilmSessionService(
    private val clientToFilmSessionRepository: ClientToFilmSessionRepository
) {

    fun add(clientToFilmSession: ClientToFilmSession) = clientToFilmSessionRepository.save(clientToFilmSession)
}