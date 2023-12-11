package com.example.film_session.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.util.Date

data class FilmSession(
    @JsonProperty("id")
    val id: Long = 0L,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("price")
    val price: Int,

    @JsonProperty("date")
    val date: Date
) {

    fun toEntity() = FilmSessionEntity(
        id = id,
        title = title,
        price = price,
        date = date.toInstant().epochSecond
    )

    companion object {
        fun fromEntity(entity: FilmSessionEntity) = FilmSession(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            date = Date.from(Instant.ofEpochSecond(entity.date))
        )
    }
}
