package com.example.client.repository

import com.example.client.model.ClientEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CrudRepository<ClientEntity, Long>