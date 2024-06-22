package com.example.todo.controllers;

import com.example.todo.handler.NotFoundException;
import com.example.todo.services.TodoService;
import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService todoService;

  @GetMapping
  public ResponseEntity<List<TodoResponseDto>> getAllTodo() {
    List<TodoResponseDto> todoResponseDtoList = todoService.getAllTodo();

    return ResponseEntity.ok(todoResponseDtoList);
  }
  @GetMapping("/{id}")
  public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) throws NotFoundException {
    TodoResponseDto todoResponseDto = todoService.getTodoById(id);

    return ResponseEntity.ok(todoResponseDto);
  }
  @PostMapping
  public void createTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
    todoService.createTodo(todoRequestDto);
  }
  @DeleteMapping("/{id}")
  public void deleteTodoById(@PathVariable Long id) {
    todoService.deleteTodoById(id);
  }
}
