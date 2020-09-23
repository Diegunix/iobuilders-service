package com.diegunix.iobuilders.commons.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.controller.dto.PaymentDto;
import com.diegunix.iobuilders.dao.domain.Payment;

@Mapper
public interface PaymentMapper {

  PaymentDto map(PaymentBean bean);

  PaymentBean map(PaymentDto dto);

  @IterableMapping(qualifiedByName = { "defaultMapper" })
  List<PaymentDto> map(Iterable<PaymentBean> content);

  PaymentBean mapEntity(Payment entity);

  Payment mapEntity(PaymentBean bean);

  @IterableMapping(qualifiedByName = { "defaultMapper" })
  List<PaymentBean> mapEntity(Iterable<Payment> content);

}