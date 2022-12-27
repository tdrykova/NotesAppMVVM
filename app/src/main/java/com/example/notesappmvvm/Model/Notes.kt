package com.example.notesappmvvm.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.Duration

@Entity(tableName = "Notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var time: String,
    var duration: String,
    var notes: String,
    var date: String,
    var priority: String
)