package com.example.client.model

import com.fasterxml.jackson.annotation.JsonProperty
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
)
