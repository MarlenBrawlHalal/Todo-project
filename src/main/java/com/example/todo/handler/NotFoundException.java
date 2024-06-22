package com.example.todo.handler;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String msg) {
    super(msg);
  }
  public NotFoundException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
