package com.diegunix.iobuilders.commons.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Error {

  GENERAL_OK("0", "GENERAL"), 
  GENERAL_KO("-999999", "An error has occurred."),
  API_ENTITY_NOT_FOUND("-400", "Entity not found."),
  API_USER_USERNAME_INCORRECT("-3200","User username incorrect."),
  API_USER_NAME_INCORRECT("-3201","User name incorrect."),
  API_USER_MAIL_INCORRECT("-3202","User mail incorrect."),
  API_USER_IS_ACTIVE("-3203","User is active."),
  API_USER_VALIDATE_INCORRECT("-3204","User validation error."),
  API_TRANSACTION_VALIDATE_INCORRECT("-3205","Transaction validation error.");

  public final String code;
  public final String description;

  private Error(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getCode() {
    return code;
  }

  public static Error getByCode(String code) {
    Error error = null;
    for (Error e : Error.values()) {
      if (e.getCode().equals(code)) {
        error = e;
      }
    }
    return error;
  }

  @Override
  public String toString() {
    return "(" + code + ")- " + description;
  }
}