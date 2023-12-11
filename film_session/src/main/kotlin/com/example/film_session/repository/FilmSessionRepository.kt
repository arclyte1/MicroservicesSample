package com.example.film_session.repository

import com.example.film_session.model.FilmSessionEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FilmSessionRepository : CrudRepository<FilmSessionEntity, Long> {

    @Query("SELECT f FROM FilmSessionEntity f WHERE f.date >= :start AND f.date <= :end")
    fun allByDateRange(@Param("start") start: Long, @Param("end") end: Long): Iterable<FilmSessionEntity>
}