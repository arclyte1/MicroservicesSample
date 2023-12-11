package com.example.client.repository

import com.example.client.model.ClientToFilmSession
import com.example.client.model.ClientToFilmSessionId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClientToFilmSessionRepository : CrudRepository<ClientToFilmSession, ClientToFilmSessionId> {

    @Query("SELECT f FROM ClientToFilmSession f WHERE f.clientId = :clientId")
    fun getAllByClientId(@Param("clientId") clientId: Long): Iterable<ClientToFilmSession>
}