package com.example.todo.repositories;

import com.example.todo.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
