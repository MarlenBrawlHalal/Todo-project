package com.example.todo.services;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.entities.TodoEntity;
import com.example.todo.entities.User;
import com.example.todo.handler.NotFoundException;
import com.example.todo.repositories.TodoRepository;
import com.example.todo.dto.TodoMapper;
import com.example.todo.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository todoRepository;
  private final UserService userService;
  private final TodoMapper todoMapper;

  public List<TodoResponseDto> getAllTodo() {

    User authenticatedUser = userService.getAuthenticatedUser();
    List<TodoEntity> todoEntities = todoRepository.findByUser(authenticatedUser);
    return todoEntities.stream().map(todoMapper::toTodoResponseDto).collect(Collectors.toList());
  }

  public TodoResponseDto getTodoById(Long id) throws NotFoundException {

    User authenticatedUser = userService.getAuthenticatedUser();
    TodoEntity todo = todoRepository.findByUserAndId(authenticatedUser, id)
        .orElseThrow(() -> new NotFoundException("Todo not found"));

    return todoMapper.toTodoResponseDto(todo);
  }

  public void createTodo(TodoRequestDto todoRequestDto) {

    User authenticatedUser = userService.getAuthenticatedUser();

    var todo = new TodoEntity();
    todo.setTitle(todoRequestDto.getTitle());
    todo.setUser(authenticatedUser);
    todoRepository.save(todo);
  }

  public void deleteTodoById(Long id) {

    User authenticatedUser = userService.getAuthenticatedUser();
    Optional<TodoEntity> todo = todoRepository.findByUserAndId(authenticatedUser, id);

    todo.ifPresent(todoRepository::delete);
  }
}
