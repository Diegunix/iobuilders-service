package com.diegunix.iobuilders.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diegunix.iobuilders.commons.error.Error;
import com.diegunix.iobuilders.commons.error.ErrorDto;
import com.diegunix.iobuilders.commons.exceptions.ApiResourceNotExitsException;
import com.diegunix.iobuilders.commons.exceptions.ApiSecurityException;
import com.diegunix.iobuilders.commons.exceptions.ApiValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class StandardController {

  @Value("${spring.application.name}")
  public String serviceName;

  protected ResponseEntity<Object> buildErrorResponse(Exception e) {
    ResponseEntity<Object> res = null;
    if (e instanceof ApiValidationException) {
      ApiValidationException ae = (ApiValidationException) e;
      res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(Error.GENERAL_OK, ae.getInfo()));
    } else if (e instanceof ApiResourceNotExitsException) {
      ApiResourceNotExitsException ae = (ApiResourceNotExitsException) e;
      res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(ae.getCode(), ae.getInfo()));
    } else if (e instanceof ApiSecurityException) {
      ApiSecurityException ae = (ApiSecurityException) e;
      res = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(ae.getCode(), ae.getInfo()));
    } else {
      res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ErrorDto(Error.GENERAL_OK, e.getMessage()));
      log.error("Ops!", e);
    }
    return res;
  }

  protected <T> ResponseEntity<T> buildResponse(ResponseEntity<T> responseEntity) {
    return responseEntity;
  }

}