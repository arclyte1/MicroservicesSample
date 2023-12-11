package com.example.film_session.service

import com.example.film_session.model.FilmSession
import com.example.film_session.repository.FilmSessionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FilmSessionService(
    private val repository: FilmSessionRepository
) {

    fun allByIds(ids: Iterable<Long>): Iterable<FilmSession> = repository.findAllById(ids).map { FilmSession.fromEntity(it) }

    fun get(id: Long): Optional<FilmSession> = repository.findById(id).map { FilmSession.fromEntity(it) }

    fun add(filmSession: FilmSession): FilmSession = FilmSession.fromEntity(repository.save(filmSession.toEntity()))

    fun edit(id: Long, filmSession: FilmSession): FilmSession = FilmSession.fromEntity(repository.save(filmSession.toEntity().copy(id = id)))

    fun remove(id: Long) = repository.deleteById(id)

    fun allByDateRange(startDate: Long, endDate: Long): Iterable<FilmSession> = repository.allByDateRange(startDate, endDate).map { FilmSession.fromEntity(it) }
}