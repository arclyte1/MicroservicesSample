package com.example.client.service

import com.example.client.model.Client
import com.example.client.model.ClientStatus
import com.example.client.repository.ClientRepository
import com.example.client.repository.ClientToFilmSessionRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientToFilmSessionRepository: ClientToFilmSessionRepository,
    private val filmSessionService: FilmSessionService
) {

    private fun getClientStatus(id: Long): ClientStatus {
        return try {
            val filmSessionIds = clientToFilmSessionRepository.getAllByClientId(id).map { it.filmSessionId }
            val clientSpentMoney = if (filmSessionIds.isNotEmpty()) {
                val filmSessions = filmSessionService.getByIds(filmSessionIds)
                filmSessions.sumOf { it.price }
            } else 0
            ClientStatus.entries.find { clientSpentMoney in it.priceRange } ?: ClientStatus.UNKNOWN
        } catch (e: Exception) {
            e.printStackTrace()
            return ClientStatus.UNKNOWN
        }
    }

    fun add(client: Client): Client {
        val savedEntity = clientRepository.save(client.toEntity())
        return Client.fromEntity(savedEntity).copy(
            clientStatus = getClientStatus(client.id)
        )
    }

    fun get(id: Long): Optional<Client> {
        return clientRepository.findById(id).getOrNull()?.let { entity ->
            val client = Client.fromEntity(entity).copy(
                clientStatus = getClientStatus(id)
            )
            Optional.of(client)
        } ?: Optional.empty()
    }
}