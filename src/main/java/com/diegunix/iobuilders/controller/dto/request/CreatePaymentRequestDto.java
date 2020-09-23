package com.diegunix.iobuilders.controller.dto.request;

import com.diegunix.iobuilders.controller.dto.PaymentDto;
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
public class CreatePaymentRequestDto {

  private double transaction;

  @JsonIgnore
  public PaymentDto getDto() {
    PaymentDto payment = new PaymentDto();
    UserDto user = new UserDto();
    payment.setTransaction(transaction);
    payment.setUser(user);
    return payment;
  }

}
