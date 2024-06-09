package com.example.todo.controllers.auth;

import com.example.todo.role.RoleRepository;
import com.example.todo.user.User;
import com.example.todo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public void register(RegistrationRequest request) {

    var userRole = roleRepository.findRoleByName("USER")
        .orElseThrow(() -> new IllegalStateException("Role USER was not initialized"));

    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .accountLocked(false)
        .enabled(false)
        .roles(List.of(userRole))
        .build();

    userRepository.save(user);
    sendValidationEmail(user);
  }
}
