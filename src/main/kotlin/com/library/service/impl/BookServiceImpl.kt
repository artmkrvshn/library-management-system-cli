package com.library.service.impl

import com.library.dto.BookUpdateRequest
import com.library.exception.BookNotFoundException
import com.library.model.Book
import com.library.repository.BookRepository
import com.library.service.BookService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(val repository: BookRepository) : BookService {

    @Transactional
    override fun save(book: Book): Long {
        return repository.save(book).id!!
    }

    override fun findAll(sort: Sort): List<Book> {
        return repository.findAllFetch()
    }

    override fun findAllFreeBooks(sort: Sort): List<Book> {
        return repository.findFreeBooksOrderById(sort)
    }

    override fun findAllBorrowedBooks(sort: Sort): List<Book> {
        return repository.findBorrowedBooksOrderById(sort)
    }

    override fun findById(id: Long): Book {
        return repository.findById(id).orElseThrow { throw BookNotFoundException(id) }
    }

    override fun findByIdFetch(id: Long): Book {
        return repository.findBookByIdFetch(id) ?: throw BookNotFoundException(id)
    }

    @Transactional
    override fun update(id: Long, request: BookUpdateRequest) {
        val book = repository.findBookByIdFetch(id) ?: throw BookNotFoundException(id)
        if (request.title != null) book.title = request.title
        if (request.published != null) book.published = request.published
        if (request.author != null) book.author = request.author
        repository.save(book)
    }

    @Transactional
    override fun delete(id: Long) {
        repository.deleteById(id)
    }

}