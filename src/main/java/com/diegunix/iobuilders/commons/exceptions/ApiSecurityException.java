package com.diegunix.iobuilders.commons.exceptions;

import com.diegunix.iobuilders.commons.error.Error;

public class ApiSecurityException extends ApiException {

  private static final long serialVersionUID = 284951347030564384L;

  public ApiSecurityException(Error error) {
    super(error);
  }

}
