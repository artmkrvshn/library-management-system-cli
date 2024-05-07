package com.library.service

import com.library.dto.ReaderUpdateRequest
import com.library.model.Reader
import org.springframework.data.domain.Sort

interface ReaderService {

    fun save(reader: Reader): Long

    fun findAll(sort: Sort = Sort.by("id")): List<Reader>

    fun findById(id: Long): Reader

    fun findByIdFetch(id: Long): Reader

    fun update(id: Long, request: ReaderUpdateRequest)

    fun delete(id: Long)

}