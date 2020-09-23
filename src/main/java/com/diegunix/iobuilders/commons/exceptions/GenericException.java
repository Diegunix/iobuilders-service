package com.diegunix.iobuilders.commons.exceptions;

import com.diegunix.iobuilders.commons.error.Error;

public class GenericException extends RuntimeException {

  private static final long serialVersionUID = -8399283084498939536L;

  public GenericException() {
  }

  public GenericException(String message) {
    super(message);
  }
  
  public GenericException(Error error) {
    super(error.getDescription());
  }

  public GenericException(String message, Throwable cause) {
    super(message, cause);
  }

  public GenericException(Throwable cause) {
    super(cause);
  }
}
