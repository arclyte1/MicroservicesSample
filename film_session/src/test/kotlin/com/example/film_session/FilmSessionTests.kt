package com.example.film_session

import com.example.film_session.model.FilmSession
import com.example.film_session.repository.FakeFilmSessionRepositoryImpl
import com.example.film_session.service.FilmSessionService
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class FilmSessionTests {

    private val service = FilmSessionService(FakeFilmSessionRepositoryImpl())
    private var addedFilmSessionId = 0L

    @Test
    fun `Save film session`() {
        val filmSession = FilmSession(
            title = "123",
            price = 100,
            date = Date.from(Instant.ofEpochSecond(123))
        )
        val addedFilmSession =  service.add(filmSession)
        addedFilmSessionId = addedFilmSession.id
        assert(filmSession.title == addedFilmSession.title)
        assert(filmSession.price == addedFilmSession.price)
        assert(filmSession.date == addedFilmSession.date)
    }

    @Test
    fun `Retrieve film session`() {
        val filmSession = FilmSession(
            title = "123",
            price = 100,
            date = Date.from(Instant.ofEpochSecond(123))
        )
        val addedFilmSession =  service.add(filmSession)
        val retrievedFilmSession = service.get(addedFilmSession.id)
        assert(retrievedFilmSession.isPresent)
    }

    @Test
    fun `Edit film session`() {
        val filmSession = FilmSession(
            title = "654",
            price = 400,
            date = Date.from(Instant.ofEpochSecond(54))
        )
        val editedFilmSession = service.edit(addedFilmSessionId, filmSession)
        assert(filmSession.title == editedFilmSession.title)
        assert(filmSession.price == editedFilmSession.price)
        assert(filmSession.date == editedFilmSession.date)
    }

    @Test
    fun `Remove film session`() {
        service.remove(addedFilmSessionId)
        val removedFilmSession =  service.get(addedFilmSessionId)
        assert(removedFilmSession.isEmpty)
    }

    @Test
    fun `Retrieve all film sessions by ids`() {
        val ids = (0..10).map {
            val filmSession = FilmSession(
                    title = "654",
                    price = 400,
                    date = Date.from(Instant.ofEpochSecond((54 + it).toLong()))
            )
            service.add(filmSession).id
        }
        val filmSessions = ids.map { service.get(it) }
        assert(filmSessions.all { it.isPresent })
    }

    @Test
    fun `Retrieve all film sessions by date range`() {
        val ids = (0..10).map {
            val filmSession = FilmSession(
                    title = "654",
                    price = 400,
                    date = Date.from(Instant.ofEpochSecond((54 + it).toLong()))
            )
            service.add(filmSession).id
        }
        assert(service.allByDateRange(startDate = 55, endDate = 59).toList().size == 5)
    }
}
