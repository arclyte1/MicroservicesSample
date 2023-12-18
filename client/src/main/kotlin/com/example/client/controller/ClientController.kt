package com.example.client.controller

import com.example.client.model.Client
import com.example.client.model.ClientToFilmSession
import com.example.client.service.AuthService
import com.example.client.service.AuthStatus
import com.example.client.service.ClientService
import com.example.client.service.ClientToFilmSessionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("client")
class ClientController(
    private val clientService: ClientService,
    private val authService: AuthService,
    private val clientToFilmSessionService: ClientToFilmSessionService
) {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) = clientService.get(id)

    @PostMapping
    fun create(
        @RequestBody client: Client,
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<Client?> {
        return when (val authStatus = authService.auth(authorization)) {
            AuthStatus.Authorized -> {
                ResponseEntity.ok(clientService.add(client))
            }
            is AuthStatus.Other -> {
                ResponseEntity.status(authStatus.statusCode).body(null)
            }
        }
    }

    @PostMapping("visit")
    fun visitFilmSession(
        @RequestBody clientToFilmSession: ClientToFilmSession,
        @RequestHeader("Authorization") authorization: String
    ): ResponseEntity<ClientToFilmSession?> {
        return when (val authStatus = authService.auth(authorization)) {
            AuthStatus.Authorized -> {
                ResponseEntity.ok(clientToFilmSessionService.add(clientToFilmSession))
            }
            is AuthStatus.Other -> {
                ResponseEntity.status(authStatus.statusCode).body(null)
            }
        }
    }
}