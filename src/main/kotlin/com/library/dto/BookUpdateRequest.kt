package com.library.dto

data class BookUpdateRequest(
    val title: String?,
    val published: Short?,
    val author: String?
)