package com.library.model

import jakarta.persistence.*

@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title")
    var title: String,

    @Column(name = "author")
    var author: String,

    @Column(name = "published")
    var published: Short,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
        name = "reader_book",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "reader_id", referencedColumnName = "id")],
    )
    var reader: Reader? = null
)