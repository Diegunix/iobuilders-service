package com.diegunix.iobuilders.commons.exceptions;

import com.diegunix.iobuilders.commons.error.Error;
import com.diegunix.iobuilders.commons.error.ErrorDto;

import lombok.Getter;
import lombok.ToString;

/**
 * Main exception class for controlled errors on API
 *
 */
@Getter
@ToString
public class ApiException extends Exception {

  private static final long serialVersionUID = 215343949476088236L;
  /**
   * Error Code
   */
  private final String code;
  /**
   * Info related
   */
  private final String info;
  /**
   * Error based on Rest link information
   */
  private final ErrorDto error;

  public ApiException(ErrorDto error) {
    super(ApiException.getMessageError(error.getCode(), error.getMessage()));
    this.code = error.getCode();
    this.info = error.getMessage();
    this.error = error;

  }

  public ApiException(Error error) {
    super(ApiException.getMessageError(error.getCode(), error.getDescription()));
    this.code = error.getCode();
    this.info = error.getDescription();
    this.error = null;
  }

  public ApiException(Error error, Object moreInfo) {
    super(ApiException.getMessageError(error.getCode(), error.getDescription()) + ", extendedInfo: " + moreInfo);
    this.code = error.getCode();
    this.info = error.getDescription() + moreInfo;
    this.error = null;
  }

  public ApiException(Exception e) {
    super(e);
    this.code = "500";
    this.info = e.getMessage();
    this.error = null;
  }

  private static String getMessageError(String error, String cause) {
    return "Error:" + error + " Cause: " + cause;
  }

}
