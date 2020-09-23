package com.diegunix.iobuilders.controller.dto.request;

import com.diegunix.iobuilders.controller.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CreateUserRequestDto {

  private String login;
  private String firstName;
  private String lastName;
  private String mail;

  @JsonIgnore
  public UserDto getDto() {
    UserDto user = new UserDto();
    user.setLogin(login);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setMail(mail);
    return user;
  }

}
