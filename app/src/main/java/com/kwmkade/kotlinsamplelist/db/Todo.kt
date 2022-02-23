package com.kwmkade.kotlinsamplelist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val tid: Int,
    @ColumnInfo(name = "todo_title") val todoTitle: String
)
