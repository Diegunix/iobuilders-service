package com.diegunix.iobuilders.commons.exceptions;

public class ConflictException extends RuntimeException {

  private static final long serialVersionUID = -8059780477337957737L;

  public ConflictException() {
  }

  public ConflictException(String message) {
    super(message);
  }

  public ConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConflictException(Throwable cause) {
    super(cause);
  }
}
