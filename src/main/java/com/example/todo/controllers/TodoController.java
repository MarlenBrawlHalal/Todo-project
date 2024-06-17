package com.example.todo.controllers;

import com.example.todo.entities.TodoEntity;
import com.example.todo.repositories.TodoRepository;
import com.example.todo.user.User;
import com.example.todo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoRepository todoRepository;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<TodoEntity>> getAllTodo() {

    User authenticatedUser = userService.getAuthenticatedUser();
    List<TodoEntity> todoEntities = todoRepository.findByUser(authenticatedUser);
    return new ResponseEntity<>(todoEntities, HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<TodoEntity> getTodoById(@PathVariable Long id) {

    User authenticatedUser = userService.getAuthenticatedUser();
    Optional<TodoEntity> todo = todoRepository.findByUserAndId(authenticatedUser, id);

    if (todo.isPresent()) {
      return new ResponseEntity<>(todo.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @PostMapping
  public ResponseEntity<?> createTodo(@RequestBody TodoEntity todo) {

    User authenticatedUser = userService.getAuthenticatedUser();
    todo.setUser(authenticatedUser);
    todoRepository.save(todo);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {

    User authenticatedUser = userService.getAuthenticatedUser();
    Optional<TodoEntity> todo = todoRepository.findByUserAndId(authenticatedUser, id);

    if (todo.isPresent()) {
      todoRepository.delete(todo.get());
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
