package com.diegunix.iobuilders.commons.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto implements Serializable {

  private static final long serialVersionUID = -4007152450833193169L;

  private String code;
  private String message;
  private String instanceId;

  public ErrorDto(Error error, String instanceId) {
    this.code = error.getCode();
    this.message = error.getDescription();
    this.instanceId = instanceId;
  }

  public ErrorDto(String code, String message) {
    this.code = code;
    this.message = message;
  }
}