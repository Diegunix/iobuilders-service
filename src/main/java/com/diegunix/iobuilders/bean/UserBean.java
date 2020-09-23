package com.diegunix.iobuilders.bean;

import java.time.LocalDateTime;

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
public class UserBean {

  private long id;
  private String login;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private Boolean active;
  private String firstName;
  private String lastName;
  private String mail;

}
