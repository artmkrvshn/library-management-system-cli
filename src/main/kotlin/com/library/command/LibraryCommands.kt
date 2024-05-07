package com.library.command

interface LibraryCommands {

    fun borrowBook(readerId: Long,  bookId: Long): String

    fun returnBook(readerId: Long, bookId: Long): String
}