package com.diegunix.iobuilders.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

  private long id;
  private String login;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private Boolean active;
  private String firstName;
  private String lastName;
  private String mail;
}
