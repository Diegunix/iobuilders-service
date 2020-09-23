package com.diegunix.iobuilders.controller;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.mappers.UserMapper;
import com.diegunix.iobuilders.controller.dto.UserDto;
import com.diegunix.iobuilders.controller.dto.request.CreateUserRequestDto;
import com.diegunix.iobuilders.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", maxAge = 3600)
public class UserController extends StandardController {

  private UserService userService;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Return all users", description = "Get all user", tags = { "user" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok",
          content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> getAllUser() {
    ResponseEntity<Object> response;
    log.info("{} getAllUser controller : Begin ", serviceName);
    try {
      List<UserBean> result = userService.getAllUser();
      response = buildResponse(ResponseEntity.status(HttpStatus.OK).body(userMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getAllUser:", e);
    }
    log.info("{} getAllUser controller : End ", serviceName);
    return response;
  }

  @ResponseBody
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create user", description = "Create a user", tags = { "user" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Ok",
          content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> createUser(@RequestBody CreateUserRequestDto dto) {
    ResponseEntity<Object> response;
    log.info("{} createUser controller : Begin ", serviceName);
    try {
      UserBean bean = userMapper.map(dto.getDto());
      UserBean result = userService.createUser(bean);
      response = buildResponse(ResponseEntity.status(HttpStatus.CREATED).body(userMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error createUser:", e);
    }
    log.info("{} createUser controller : End ", serviceName);
    return response;
  }

  @ResponseBody
  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Update user", description = "Update a user", tags = { "user" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok",
          content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody CreateUserRequestDto dto) {
    ResponseEntity<Object> response;
    log.info("{} updateUser controller : Begin ", serviceName);
    try {
      UserBean bean = userMapper.map(dto.getDto());
      bean.setId(id);
      response = buildResponse(ResponseEntity.status(HttpStatus.OK).body(userMapper.map(userService.updateUser(bean))));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error updateUser:", e);
    }
    log.info("{} updateUser controller : End ", serviceName);
    return response;
  }

  @ResponseBody
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Delete user", description = "Delete a user", tags = { "user" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Ok"),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("{} deleteUser controller : Begin ", serviceName);
    try {
      userService.deleteUser(id);
      response = buildResponse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error deleteUser:", e);
    }
    log.info("{} deleteUser controller : End ", serviceName);
    return response;
  }

  @ResponseBody
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Return user by id", description = "Get user by id", tags = { "user" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok",
          content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request."),
      @ApiResponse(responseCode = "500", description = "Internal server problems.") })
  public ResponseEntity<Object> getOneUser(@PathVariable("id") Long id) {
    ResponseEntity<Object> response;
    log.info("{} getOneUser controller : Begin ", serviceName);
    try {
      UserBean result = userService.getUser(id);
      response = buildResponse(ResponseEntity.status(HttpStatus.OK).body(userMapper.map(result)));
    } catch (Exception e) {
      response = buildErrorResponse(e);
      log.error("Error getOneUser:", e);
    }
    log.info("{} getOneUser controller : End ", serviceName);
    return response;
  }
}