package com.example.client.service

import com.example.client.model.FilmSession
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Repository
class FilmSessionService(
    private val restTemplateBuilder: RestTemplateBuilder
) {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    fun getByIds(ids: Iterable<Long>): Iterable<FilmSession> {
        val url = "http://film-session:8080/film_session?ids=${ids.joinToString(",")}"
        return restTemplate.exchange<List<FilmSession>>(
                url,
                method = HttpMethod.GET
        ).body ?: emptyList()
    }
}