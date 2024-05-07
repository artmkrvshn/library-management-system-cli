package com.library.command.impl

import com.library.command.LibraryCommands
import com.library.service.LibraryService
import org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE
import org.springframework.shell.command.annotation.Command
import org.springframework.shell.command.annotation.Option

@Command(command = ["library"])
class LibraryCommandsImpl(private val libraryService: LibraryService) : LibraryCommands {

    @Command(command = ["borrow"], description = "Borrow a book")
    override fun borrowBook(
        @Option(longNames = ["readerId"], required = true, arity = EXACTLY_ONE) readerId: Long,
        @Option(longNames = ["bookId"], required = true, arity = EXACTLY_ONE) bookId: Long
    ): String {
        libraryService.borrowBook(readerId, bookId)
        return "A book was borrowed"
    }

    @Command(command = ["return"], description = "Return a book")
    override fun returnBook(
        @Option(longNames = ["readerId"], required = true, arity = EXACTLY_ONE) readerId: Long,
        @Option(longNames = ["bookId"], required = true, arity = EXACTLY_ONE) bookId: Long
    ): String {
        libraryService.returnBook(readerId, bookId)
        return "A book was returned"
    }

}