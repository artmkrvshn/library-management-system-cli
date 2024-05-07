package com.library.table

import com.library.model.Reader
import org.springframework.shell.table.ArrayTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder

class ReadersTable(private val entities: List<Reader>) : EntityTable<Reader> {

    override fun render(width: Int): String {
        val data = mutableListOf(
            arrayOf("id", "name", "email", "birthday"),
            *entities.map { toRow(it) }.toTypedArray()
        )
        val model = ArrayTableModel(data.toTypedArray())
        val table = TableBuilder(model)
        table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light)
        return table.build().render(width)
    }

    override fun toRow(entity: Reader): Array<String> {
        return arrayOf(entity.id.toString(), entity.name, entity.email, entity.birthday.toString())
    }
}