package com.survivalcoding.gangnam2kiandroidstudy.presentation.todo

import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val dataSource: TodoDataSource) : TodoRepository {
    override suspend fun getTodos(): List<Todo> {
        return dataSource.getTodos()
    }

    override suspend fun addTodo(todo: Todo) {
        dataSource.addTodo(todo)
    }
}
