package com.library.service

import com.library.dto.BookUpdateRequest
import com.library.model.Book
import org.springframework.data.domain.Sort

interface BookService {

    fun save(book: Book): Long

    fun findAll(sort: Sort = Sort.by("id")): List<Book>

    fun findAllFreeBooks(sort: Sort = Sort.by("id")): List<Book>

    fun findAllBorrowedBooks(sort: Sort = Sort.by("id")): List<Book>

    fun findById(id: Long): Book

    fun findByIdFetch(id: Long): Book

    fun update(id: Long, request: BookUpdateRequest)

    fun delete(id: Long)
}