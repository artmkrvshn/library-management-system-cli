package com.library.table

import com.library.model.Book
import org.springframework.shell.table.ArrayTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder

class BooksTable(private val entities: List<Book>) : EntityTable<Book> {

    override fun render(width: Int): String {
        val data = mutableListOf(
            arrayOf("id", "title", "published", "author", "reader_id"),
            *entities.map { toRow(it) }.toTypedArray()
        )
        val model = ArrayTableModel(data.toTypedArray())
        val table = TableBuilder(model)
        table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light)
        return table.build().render(width)
    }

    override fun toRow(entity: Book): Array<String> {
        return arrayOf(
            entity.id.toString(),
            entity.title,
            entity.published.toString(),
            entity.author,
            entity.reader?.id?.toString() ?: "null"
        )
    }
}