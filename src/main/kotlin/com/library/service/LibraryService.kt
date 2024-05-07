package com.library.service

interface LibraryService {

    fun borrowBook(readerId: Long, bookId: Long)

    fun returnBook(readerId: Long, bookId: Long)

}