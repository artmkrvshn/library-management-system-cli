package com.library.service.impl

import com.library.dto.ReaderUpdateRequest
import com.library.exception.ReaderNotFoundException
import com.library.model.Reader
import com.library.repository.ReaderRepository
import com.library.service.ReaderService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ReaderServiceImpl(val repository: ReaderRepository) : ReaderService {

    @Transactional
    override fun save(reader: Reader): Long {
        // todo: save and flush?
        return repository.save(reader).id!!
    }

    fun findAll(): List<Reader> {
        return repository.findAll()
    }

    override fun findAll(sort: Sort): List<Reader> {
        return repository.findAll(sort)
    }

    override fun findById(id: Long): Reader {
        return repository.findReaderById(id) ?: throw ReaderNotFoundException(id)
    }

    override fun findByIdFetch(id: Long): Reader {
        return repository.findReaderByIdFetch(id) ?: throw ReaderNotFoundException(id)
    }

    @Transactional
    override fun update(id: Long, request: ReaderUpdateRequest) {
        val reader = repository.findReaderByIdFetch(id) ?: throw ReaderNotFoundException(id)
        if (request.name != null) reader.name = request.name
        if (request.email != null) reader.email = request.email
        if (request.birthday != null) reader.birthday = request.birthday
        repository.save(reader)
    }

    @Transactional
    override fun delete(id: Long) {
        repository.deleteById(id)
    }
}