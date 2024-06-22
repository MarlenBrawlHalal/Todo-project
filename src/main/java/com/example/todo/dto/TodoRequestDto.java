package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder почему-то из-за него Jackson не работает, а в других дто работает
public class TodoRequestDto {

  @NotEmpty(message = "Title can not be empty")
  @NotBlank(message = "Title can not be empty")
  private String title;
}
