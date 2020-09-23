package com.diegunix.iobuilders.commons.exceptions;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 8291560608992532459L;

  public BadRequestException() {
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }
}
