package com.example.todo.controllers;

import com.example.todo.entities.TodoEntity;
import com.example.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todo")
public class TodoController {

  private final TodoRepository todoRepository;

  @Autowired
  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }
  @GetMapping
  public ResponseEntity<List<TodoEntity>> getAllTodo() {
    List<TodoEntity> todoEntities = todoRepository.findAll();
    return new ResponseEntity<>(todoEntities, HttpStatus.OK);
  }
  @GetMapping("/{id}")
  public ResponseEntity<TodoEntity> getTodoById(@PathVariable Long id) {
    Optional<TodoEntity> todo = todoRepository.findById(id);
    if (todo.isPresent()) {
      return new ResponseEntity<>(todo.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @PostMapping
  public ResponseEntity<?> createTodo(@RequestBody TodoEntity todo) {
    todoRepository.save(todo);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
    todoRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
