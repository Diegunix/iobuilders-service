package com.diegunix.iobuilders.commons.exceptions;

import com.diegunix.iobuilders.commons.error.Error;

public class ApiResourceNotExitsException extends ApiException {

  private static final long serialVersionUID = -4649054807676182346L;

  public ApiResourceNotExitsException(Error error) {
    super(error);
  }

  public ApiResourceNotExitsException(Error error, Object moreInfo) {
    super(error, moreInfo);
  }

}
