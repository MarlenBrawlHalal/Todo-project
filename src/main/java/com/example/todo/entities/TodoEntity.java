package com.example.todo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
@EntityListeners(AuditingEntityListener.class)
public class TodoEntity {

  @Id
  @GeneratedValue
  private Long id;
  @NotBlank(message = "Title can not be empty")
  private String title;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;
}
