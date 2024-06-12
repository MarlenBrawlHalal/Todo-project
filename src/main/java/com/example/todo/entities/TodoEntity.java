package com.example.todo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "_todo")
public class TodoEntity {

  @Id
  @GeneratedValue
  private Long id;
  @NotBlank(message = "Title can not be empty")
  private String title;

  private Date createdAt = new Date();
}
