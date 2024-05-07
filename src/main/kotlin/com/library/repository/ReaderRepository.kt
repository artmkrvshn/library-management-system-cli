package com.library.repository

import com.library.model.Reader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ReaderRepository : JpaRepository<Reader, Long> {

    fun findReaderById(id: Long): Reader?

    @Query("select r from Reader r left join fetch r.books where r.id = :id")
    fun findReaderByIdFetch(id: Long): Reader?

}