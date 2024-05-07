package com.library.table

interface EntityTable<T> {

    fun render(width: Int): String

    fun toRow(entity: T): Array<String>
}