package com.example.todo.entities;

import com.example.todo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "todo")
public class TodoEntity {

  @Id
  @GeneratedValue
  private Long id;
  @NotBlank(message = "Title can not be empty")
  private String title;

  private Date createdAt = new Date();

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  @JsonIgnore
  private User user;
}
