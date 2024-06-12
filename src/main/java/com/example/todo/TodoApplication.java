package com.example.todo;

import com.example.todo.role.Role;
import com.example.todo.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class TodoApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(RoleRepository roleRepository) {
    return args -> {
      if (roleRepository.findRoleByName("USER").isEmpty()) {
        roleRepository.save(
            Role.builder()
                .name("USER")
                .build()
        );
      }
    };
  }

}
