package com.library.repository

import com.library.model.Book
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {

    @Query("select b from Book b left join fetch b.reader order by b.id")
    fun findAllFetch(): List<Book>

    @Query("select b from Book b left join fetch b.reader where b.id = :bookId order by b.id")
    fun findBookByIdFetch(bookId: Long): Book?

    @Query("select b from Book b left join fetch b.reader r where r.id is not null")
    fun findBorrowedBooksOrderById(sort: Sort): List<Book>

    @Query("select b from Book b left join fetch b.reader r where r.id is null")
    fun findFreeBooksOrderById(sort: Sort): List<Book>

}