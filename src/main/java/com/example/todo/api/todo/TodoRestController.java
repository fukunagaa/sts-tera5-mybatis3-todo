package com.example.todo.api.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;
import com.github.dozermapper.core.Mapper;

@RestController
@RequestMapping("todos")
public class TodoRestController {

	@Inject
	TodoService todoService;
	@Inject
	Mapper beanMapper;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TodoResource> getTodos() {
		Collection<Todo> todos = todoService.findAll();
		List<TodoResource> todoResources = new ArrayList<>();
		for (Todo todo : todos) {
			todoResources.add(beanMapper.map(todo, TodoResource.class));
		}
		return todoResources;
	}
}
