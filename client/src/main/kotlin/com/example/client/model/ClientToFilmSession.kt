package com.example.client.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.io.Serializable

class ClientToFilmSessionId(
    val clientId: Long = 0L,
    val filmSessionId: Long = 0L
) : Serializable

@Entity
@Table(name = "client_to_film_session")
@IdClass(ClientToFilmSessionId::class)
data class ClientToFilmSession(
    @Id val clientId: Long = 0L,
    @Id val filmSessionId: Long = 0L
)
