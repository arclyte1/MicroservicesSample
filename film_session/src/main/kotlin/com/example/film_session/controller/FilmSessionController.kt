package com.example.film_session.controller

import com.example.film_session.model.FilmSession
import com.example.film_session.service.FilmSessionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("film_session")
class FilmSessionController(
    private val service: FilmSessionService
) {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) = service.get(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody filmSession: FilmSession) = service.add(filmSession)

    @PutMapping("{id}")
    fun update(
            @PathVariable id: Long,
            @RequestBody filmSession: FilmSession
    ) = service.edit(id, filmSession)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = service.remove(id)

    @GetMapping
    fun getAll(
        @RequestParam("ids", required = false) ids: String? = null,
        @RequestParam("start_date", required = false) startDate: Date? = null,
        @RequestParam("end_date", required = false) endDate: Date? = null
    ): Iterable<FilmSession> {
        if (ids != null) {
            return service.allByIds(ids.split(",").map(String::toLong))
        }
        if (startDate != null && endDate != null) {
            return service.allByDateRange(startDate = startDate.toInstant().epochSecond, endDate = endDate.toInstant().epochSecond)
        }
        return emptyList()
    }
}