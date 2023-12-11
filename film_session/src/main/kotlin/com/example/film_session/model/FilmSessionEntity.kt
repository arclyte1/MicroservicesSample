package com.example.film_session.model

import jakarta.persistence.*

@Entity
@Table(name = "film_session")
data class FilmSessionEntity(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,

    @Column(name = "title")
    val title: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "date")
    val date: Long
)
