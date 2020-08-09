package com.example.todo.app;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.app.TodoForm.TodoCreate;
import com.example.todo.app.TodoForm.TodoDelete;
import com.example.todo.app.TodoForm.TodoFinish;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Inject
	TodoService todoService;

	@Inject
	Mapper beanMapper;

	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm form = new TodoForm();
		return form;
	}

	@GetMapping("list")
	public String list(Model model) {
		Collection<Todo> todos = todoService.findAll();
		model.addAttribute("todos", todos);
		return "todo/list";
	}

	@PostMapping("createTodo")
	public String createTodo(@Validated({ Default.class, TodoCreate.class }) TodoForm todoForm,
			BindingResult bindingResult, Model model, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(model);
		}

		Todo todo = beanMapper.map(todoForm, Todo.class);

		try {
			todoService.createTodo(todo);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(model);
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));

		return "redirect:/todo/list";
	}

	@PostMapping("finishTodo")
	public String finishTodo(@Validated({ Default.class, TodoFinish.class }) TodoForm form, BindingResult bindingResult,
			Model model, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return list(model);
		}

		try {
			todoService.finishTodo(form.getTodoId());
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(model);
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Finished successfully!")));
		return "redirect:/todo/list";
	}

	@PostMapping("deleteTodo")
	public String delete(@Validated({ Default.class, TodoDelete.class }) TodoForm form, BindingResult bindingResult,
			Model model, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(model);
		}

		try {
			todoService.deleteTodo(form.getTodoId());
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(model);
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Deleted successfully!")));
		return "redirect:/todo/list";
	}
}
