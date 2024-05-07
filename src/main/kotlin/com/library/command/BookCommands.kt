package com.library.command

interface BookCommands {

    fun create(title: String, published: Short, author: String): String

    fun readAll(): String

    fun readAllFreeBooks(): String

    fun readAllBorrowedBooks(): String

    fun readById(id: Long): String

    fun updateById(id: Long, title: String?, published: Short?, author: String?): String

    fun deleteById(id: Long): String
}