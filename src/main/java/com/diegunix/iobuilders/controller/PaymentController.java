package com.diegunix.iobuilders.controller;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.commons.mappers.PaymentMapper;
import com.diegunix.iobuilders.controller.dto.PaymentDto;
import com.diegunix.iobuilders.controller.dto.request.CreatePaymentRequestDto;
import com.diegunix.iobuilders.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/transaction")
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class PaymentController extends StandardController {

  private PaymentService paymentService;

  private PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @ResponseBody
  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Return all transaction by user", description = "Get all transaction by user",
      tags = { "transaction" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok",
          content = @Content(schema = @Schema(implementation = PaymentDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> getAllTransactionByUser(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("{} getAllTransactionByUser controller : Begin ", serviceName);
    try {
      List<PaymentBean> result = paymentService.getAllTransactionByUser(id);
      response = buildResponse(ResponseEntity.status(HttpStatus.OK).body(paymentMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getAllTransactionByUser:", e);
    }
    log.info("{} getAllTransactionByUser controller : End ", serviceName);
    return response;
  }

  @ResponseBody
  @PostMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create transaction", description = "Create a transaction", tags = { "transaction" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Ok",
          content = @Content(schema = @Schema(implementation = PaymentDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> createTransaction(@PathVariable("id") Long id,
      @RequestBody CreatePaymentRequestDto dto) {
    ResponseEntity<Object> response;
    log.info("{} createTransaction controller : Begin ", serviceName);
    try {
      PaymentBean bean = paymentMapper.map(dto.getDto());
      bean.getUser().setId(id);
      PaymentBean result = paymentService.createTransaction(bean);
      response = buildResponse(ResponseEntity.status(HttpStatus.CREATED).body(paymentMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error createTransaction:", e);
    }
    log.info("{} createTransaction controller : End ", serviceName);
    return response;
  }
  
  @ResponseBody
  @GetMapping(value = "/user/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Return all transaction by user", description = "Get all transaction by user",
      tags = { "transaction" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok",
          content = @Content(schema = @Schema(implementation = PaymentDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> getAmountByUser(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("{} getAmountByUser controller : Begin ", serviceName);
    try {
      PaymentBean result = paymentService.getAmountByUser(id);
      response = buildResponse(ResponseEntity.status(HttpStatus.OK).body(paymentMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getAmountByUser:", e);
    }
    log.info("{} getAmountByUser controller : End ", serviceName);
    return response;
  }

}