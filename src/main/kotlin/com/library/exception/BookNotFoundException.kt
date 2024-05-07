package com.library.exception

class BookNotFoundException : EntityNotFoundException {

    private companion object {
        private const val DEFAULT_MESSAGE = "Book with this ID [%d] doesn't exist"
    }

    constructor(message: String?) : super(message)
    constructor(id: Long, message: String? = DEFAULT_MESSAGE.format(id)) : super(message)
    constructor(id: Long, message: String? = DEFAULT_MESSAGE.format(id), cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

}