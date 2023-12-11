package com.example.client.model

import jakarta.persistence.*

@Entity
@Table(name = "client")
data class ClientEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        val id: Long = 0L,

        @Column(name = "phone")
        val phone: String,
)
