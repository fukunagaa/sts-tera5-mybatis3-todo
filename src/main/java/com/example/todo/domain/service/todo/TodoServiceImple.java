package com.example.todo.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImple implements TodoService {
	
	private static final long MAX_UNFINISHED_COUNT = 5;
	
	@Inject
	TodoRepository todoRepository;

	private Todo findTodo(String todoId) {
		Todo todo = todoRepository.findById(todoId).orElse(null);
		if (todo == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E404] The requested Todo is not found. (id=" + todoId + ")"));
			throw new ResourceNotFoundException(messages);
		}
		return todo;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Todo> findAll() {
		return todoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Todo findOne(String todoId) {
		Todo todo = todoRepository.findById(todoId).orElse(null);
		if(todo == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E404] The requested Todo is not found. (id=" + todoId + ")"));
			throw new ResourceNotFoundException(messages);
		}
		return todo; 
	}

	@Override
	public Todo createTodo(Todo todo) {
		long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E001] The count of un-finished Todo must not be over "
                            + MAX_UNFINISHED_COUNT + "."));
            throw new BusinessException(messages);
        }
        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();
        
        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);
        
		todoRepository.createTodo(todo);
		return todo;
	}

	@Override
	public Todo finishTodo(String todoId) {
		Todo todo = findTodo(todoId);
        if (todo.isFinished()) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E002] The requested Todo is already finished. (id="
                            + todoId + ")"));
            throw new BusinessException(messages);
        }
        todo.setFinished(true);
        todoRepository.updateTodo(todo);
        return todo;
	}

	@Override
	public void deleteTodo(String todoId) {
		Todo todo = findTodo(todoId);
        todoRepository.deleteTodo(todo);
	}

}
