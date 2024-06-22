package com.example.todo.controllers;

import com.example.todo.dto.AuthenticationRequestDto;
import com.example.todo.dto.AuthenticationResponseDto;
import com.example.todo.services.AuthenticationService;
import com.example.todo.dto.RegistrationRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

  private final AuthenticationService authService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<?> register(
      @RequestBody @Valid RegistrationRequestDto request
  ) throws MessagingException {
    authService.register(request);
    return ResponseEntity.accepted().build();
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @RequestBody @Valid AuthenticationRequestDto request
  ) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @GetMapping("/activate-account")
  public void confirm(
      @RequestParam String token
  ) throws MessagingException {
    authService.activateAccount(token);
  }

}
