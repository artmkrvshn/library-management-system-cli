package com.library.command

import java.time.LocalDate

interface ReaderCommands {

    fun create(name: String, email: String, birthday: LocalDate): String

    fun readAll(): String

    fun readById(id: Long): String

    fun updateById(id: Long, name: String?, email: String?, birthday: LocalDate?): String

    fun deleteById(id: Long): String
}