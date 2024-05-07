package com.library.command.impl

import com.library.command.ReaderCommands
import com.library.dto.ReaderUpdateRequest
import com.library.model.Reader
import com.library.service.ReaderService
import com.library.table.BooksTable
import com.library.table.ReadersTable
import org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE
import org.springframework.shell.command.annotation.Command
import org.springframework.shell.command.annotation.Option
import java.time.LocalDate

@Command(command = ["readers"])
class ReaderCommandsImpl(private val readerService: ReaderService) : ReaderCommands {

    @Command(command = ["create"], description = "Create a book")
    override fun create(
        @Option(longNames = ["name"], required = true, arity = EXACTLY_ONE) name: String,
        @Option(longNames = ["email"], required = true, arity = EXACTLY_ONE) email: String,
        @Option(longNames = ["birthday"], required = true, arity = EXACTLY_ONE) birthday: LocalDate
    ): String {
        val reader = Reader(name = name, email = email, birthday = birthday)
        readerService.save(reader)
        return "Reader successfully created"
    }

    @Command(description = "Read all readers")
    override fun readAll(): String {
        val readers = readerService.findAll()
        return ReadersTable(readers).render(100)
    }

    @Command(command = ["id"], description = "Read a book by id")
    override fun readById(@Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long): String {
        val reader = readerService.findByIdFetch(id)
        val readerString = "${reader.id}, ${reader.name}, ${reader.email}, ${reader.birthday}"
        val sb = StringBuilder(readerString).apply {
            appendLine()
            if (reader.books.isNotEmpty()) {
                append(BooksTable(reader.books).render(100))
            } else {
                append("This reader doesn't have books")
            }
            appendLine()
        }
        return sb.toString()
    }

    @Command(command = ["update"], description = "Update a reader by id")
    override fun updateById(
        @Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long,
        @Option(longNames = ["name"], arity = EXACTLY_ONE) name: String?,
        @Option(longNames = ["email"], arity = EXACTLY_ONE) email: String?,
        @Option(longNames = ["birthday"], arity = EXACTLY_ONE) birthday: LocalDate?
    ): String {
        val request = ReaderUpdateRequest(name, email, birthday)
        readerService.update(id, request)
        return "Reader updated"
    }


    @Command(command = ["delete"], description = "Delete a reader by id")
    override fun deleteById(@Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long): String {
        readerService.delete(id)
        return "Reader was deleted"
    }

}