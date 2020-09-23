package com.diegunix.iobuilders.commons.exceptions;

import com.diegunix.iobuilders.commons.error.Error;

public class ApiValidationException extends ApiException {

  private static final long serialVersionUID = 8814568767835366650L;

  public ApiValidationException(Error error) {
    super(error);
  }

  public ApiValidationException(Error error, Object moreInfo) {
    super(error, moreInfo);
  }

}
