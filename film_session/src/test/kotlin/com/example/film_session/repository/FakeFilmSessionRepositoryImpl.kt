package com.example.film_session.repository

import com.example.film_session.model.FilmSessionEntity
import java.util.*

class FakeFilmSessionRepositoryImpl : FilmSessionRepository {
    override fun allByDateRange(start: Long, end: Long): Iterable<FilmSessionEntity> {
        val found = data.filter { it.date in start..end }
        return found
    }

    override fun <S : FilmSessionEntity?> save(entity: S & Any): S & Any {
        val entityToAdd = entity.copy(id = ++lastId)
        data.add(entityToAdd)
        return entityToAdd as (S & Any)
    }

    override fun <S : FilmSessionEntity?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        val entitiesToAdd = entities.map {
            it!!.copy(id = ++lastId)
        }
        data.addAll(entitiesToAdd)
        return entitiesToAdd.toMutableList() as MutableIterable<S>
    }

    override fun findAll(): MutableIterable<FilmSessionEntity> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<FilmSessionEntity> {
        val found = data.filter { it.id in ids }
        return found.toMutableList()
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: FilmSessionEntity) {
        data.remove(entity)
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<FilmSessionEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        data.removeIf { it.id == id }
    }

    override fun existsById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Optional<FilmSessionEntity> {
        return data.find { it.id == id }?.let {
            Optional.of(it)
        } ?: Optional.empty()
    }

    companion object {
        private var lastId = 0L;
        private val data = mutableListOf<FilmSessionEntity>()
    }
}