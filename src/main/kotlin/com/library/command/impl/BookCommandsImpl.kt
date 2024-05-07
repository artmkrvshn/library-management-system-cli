package com.library.command.impl

import com.library.command.BookCommands
import com.library.dto.BookUpdateRequest
import com.library.model.Book
import com.library.service.BookService
import com.library.table.BooksTable
import org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE
import org.springframework.shell.command.annotation.Command
import org.springframework.shell.command.annotation.Option

@Command(command = ["books"])
class BookCommandsImpl(private val bookService: BookService) : BookCommands {

    @Command(command = ["create"], description = "Create a book")
    override fun create(
        @Option(longNames = ["title"], required = true, arity = EXACTLY_ONE) title: String,
        @Option(longNames = ["published"], required = true, arity = EXACTLY_ONE) published: Short,
        @Option(longNames = ["author"], required = true, arity = EXACTLY_ONE) author: String
    ): String {
        val book = Book(title = title, author = author, published = published)
        bookService.save(book)
        return "Book successfully created"
    }

    @Command(description = "Read all books")
    override fun readAll(): String {
        val books = bookService.findAll()
        return BooksTable(books).render(100)
    }

    @Command(command = ["free"], description = "Read all free books")
    override fun readAllFreeBooks(): String {
        val books = bookService.findAllFreeBooks()
        return BooksTable(books).render(100)
    }

    @Command(command = ["borrowed"], description = "Read all borrowed books")
    override fun readAllBorrowedBooks(): String {
        val books = bookService.findAllBorrowedBooks()
        return BooksTable(books).render(100)
    }

    @Command(command = ["id"], description = "Read a book by id")
    override fun readById(@Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long): String {
        val book = bookService.findByIdFetch(id)
        return "${book.id}, ${book.title}, ${book.published}, ${book.author}, ${book.reader?.id ?: "Free"}"
    }

    @Command(command = ["update"], description = "Update a book by id")
    override fun updateById(
        @Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long,
        @Option(longNames = ["title"], arity = EXACTLY_ONE) title: String?,
        @Option(longNames = ["published"], arity = EXACTLY_ONE) published: Short?,
        @Option(longNames = ["author"], arity = EXACTLY_ONE) author: String?
    ): String {
        val request = BookUpdateRequest(title, published, author)
        bookService.update(id, request)
        return "Book updated"
    }

    @Command(command = ["delete"], description = "Delete a book by id")
    override fun deleteById(@Option(longNames = ["id"], required = true, arity = EXACTLY_ONE) id: Long): String {
        bookService.delete(id)
        return "Book was deleted"
    }

}