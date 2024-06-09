package com.example.todo.controllers.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

  @NotEmpty(message = "Firstname shouldn't be empty")
  @NotBlank(message = "Firstname shouldn't be empty")
  private String firstname;

  @NotEmpty(message = "Lastname shouldn't be empty")
  @NotBlank(message = "Lastname shouldn't be empty")
  private String lastname;

  @Email(message = "Email is not formatted")
  @NotEmpty(message = "Email shouldn't be empty")
  @NotBlank(message = "Email shouldn't be empty")
  private String email;

  @NotEmpty(message = "Password shouldn't be empty")
  @NotBlank(message = "Password shouldn't be empty")
  @Size(min = 8, message = "Password should be at least 8 characters")
  private String password;
}
