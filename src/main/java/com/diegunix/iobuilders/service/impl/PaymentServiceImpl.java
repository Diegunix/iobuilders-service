package com.diegunix.iobuilders.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.commons.mappers.PaymentMapper;
import com.diegunix.iobuilders.commons.mappers.UserMapper;
import com.diegunix.iobuilders.dao.domain.Payment;
import com.diegunix.iobuilders.dao.repository.PaymentRepository;
import com.diegunix.iobuilders.service.PaymentService;
import com.diegunix.iobuilders.service.UserService;
import com.diegunix.iobuilders.service.business.PaymentBusinessService;

@Service
public class PaymentServiceImpl implements PaymentService {

  private PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  private PaymentRepository paymenyRepository;
  private PaymentBusinessService paymentBusiness;
  private UserService userService;

  @Autowired
  public PaymentServiceImpl(PaymentRepository paymenyRepository, PaymentBusinessService paymentBusiness,
      UserService userService) {
    this.paymenyRepository = paymenyRepository;
    this.paymentBusiness = paymentBusiness;
    this.userService = userService;
  }

  @Transactional
  @Override
  public PaymentBean createTransaction(PaymentBean transaction) throws ApiException {
    paymentBusiness.validation(transaction);
    UserBean user = userService.getUser(transaction.getUser().getId());
    transaction.setUser(user);
    transaction.setCreatedDate(LocalDateTime.now());
    Payment saved = paymenyRepository.save(paymentMapper.mapEntity(transaction));
    return paymentMapper.mapEntity(saved);
  }

  @Override
  public PaymentBean getAmountByUser(Long id) throws ApiException {
    List<PaymentBean> transactions = getAllTransactionByUser(id);
    return paymentBusiness.getAmount(transactions);
  }

  @Override
  public List<PaymentBean> getAllTransactionByUser(Long id) throws ApiException {
    UserBean user = userService.getUser(id);
    List<Payment> result = paymenyRepository.findByUser(userMapper.mapEntity(user));
    return paymentMapper.mapEntity(result);
  }

}
