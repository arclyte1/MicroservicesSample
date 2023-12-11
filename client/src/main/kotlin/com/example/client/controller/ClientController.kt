package com.example.client.controller

import com.example.client.model.Client
import com.example.client.model.ClientToFilmSession
import com.example.client.service.ClientService
import com.example.client.service.ClientToFilmSessionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("client")
class ClientController(
    private val service: ClientService,
    private val clientToFilmSessionService: ClientToFilmSessionService
) {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) = service.get(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody client: Client) = service.add(client)

    @PostMapping("visit")
    fun visitFilmSession(@RequestBody clientToFilmSession: ClientToFilmSession) = clientToFilmSessionService.add(clientToFilmSession)
}