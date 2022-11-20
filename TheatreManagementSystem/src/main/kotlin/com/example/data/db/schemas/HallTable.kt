package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object HallTable: Table("halls") {
    /**
     * ## Unique name of Hall
     * ## PRIMARY KEY
     * *Example* - `Sala Numer 1`
     */
    val hallName = varchar("hall_name", 256)

    /**
     * ## Path to backgroud image used in background
     * *Example* - `img/halls/sala_1_topdown.svg`
     */
    val backgroundPath = varchar("background_path", 256)

    override val primaryKey = PrimaryKey(hallName)
}