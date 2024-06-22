package com.example.todo.repositories;

import com.example.todo.entities.TodoEntity;
import com.example.todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

  List<TodoEntity> findByUser(User user);
  Optional<TodoEntity> findByUserAndId(User user, Long id);
}
