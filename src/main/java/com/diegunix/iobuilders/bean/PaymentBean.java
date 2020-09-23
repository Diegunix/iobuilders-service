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
public class PaymentBean {

  private long id;
  private UserBean user;
  private LocalDateTime createdDate;
  private double transaction;

}
