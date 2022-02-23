package com.kwmkade.kotlinsamplelist.db

import androidx.annotation.WorkerThread

class TodoRepository(private val todoDao: TodoDao) {
    val allTodoList = todoDao.getAll()

    @WorkerThread
    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    @WorkerThread
    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}