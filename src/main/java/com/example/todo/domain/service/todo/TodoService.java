package com.example.todo.domain.service.todo;

import java.util.Collection;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	Collection<Todo> findAll();
	
	Todo findOne(String todoId);

	Todo createTodo(Todo todo);

	Todo finishTodo(String todoId);

	void deleteTodo(String todoId);
}
