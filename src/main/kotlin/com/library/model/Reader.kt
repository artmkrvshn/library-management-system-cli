package com.library.model

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Entity
@Table(name = "reader")
data class Reader(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    var birthday: LocalDate,

    @OneToMany(mappedBy = "reader", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var books: MutableList<Book> = mutableListOf(),
)