package com.library.service.impl

import com.library.service.BookService
import com.library.service.LibraryService
import com.library.service.ReaderService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LibraryServiceImpl(
    private val readerService: ReaderService,
    private val bookService: BookService
) : LibraryService {

    @Transactional
    override fun borrowBook(readerId: Long, bookId: Long) {
        val bookToBorrow = bookService.findByIdFetch(bookId)
        if (bookToBorrow.reader == null) {
            val reader = readerService.findById(readerId)
            bookToBorrow.reader = reader
        } else {
            // todo: create a custom exception
            val reader = bookToBorrow.reader!!
            throw RuntimeException("A book ${bookToBorrow.title} [$bookId] has a reader ${reader.name} [${reader.id}]")
        }
    }

    @Transactional
    override fun returnBook(readerId: Long, bookId: Long) {
        val reader = readerService.findById(readerId)
        val bookToReturn = bookService.findByIdFetch(bookId)

        if (bookToReturn.reader == reader) {
            bookToReturn.reader = null
        } else {
            // todo: create a custom exception
            throw RuntimeException("A reader ${reader.name} [${reader.id}] doesn't have ${bookToReturn.title} [$bookId] book")
        }
    }

}