package com.example.todo.dto;

import com.example.todo.entities.TodoEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoMapper {

  public TodoResponseDto toTodoResponseDto(TodoEntity todoEntity) {
    return new TodoResponseDto(todoEntity.getTitle());
  }
}
