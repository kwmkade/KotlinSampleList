package com.kwmkade.kotlinsamplelist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>
}