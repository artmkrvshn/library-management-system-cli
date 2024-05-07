package com.library.dto

import java.time.LocalDate

data class ReaderUpdateRequest(
    val name: String?,
    val email: String?,
    val birthday: LocalDate?,
)