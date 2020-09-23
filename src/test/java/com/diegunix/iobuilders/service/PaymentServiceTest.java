package com.diegunix.iobuilders.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.commons.mappers.UserMapper;
import com.diegunix.iobuilders.dao.domain.Payment;
import com.diegunix.iobuilders.dao.domain.UserIoBuilder;
import com.diegunix.iobuilders.dao.repository.PaymentRepository;
import com.diegunix.iobuilders.service.business.PaymentBusinessService;
import com.diegunix.iobuilders.service.impl.PaymentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  PaymentService service;

  private PaymentRepository repository;
  private PaymentBusinessService businessService;
  private UserService userService;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Before
  public void prepareEnvironment() {
    repository = mock(PaymentRepository.class);
    businessService = new PaymentBusinessService();
    userService = mock(UserService.class);
    service = new PaymentServiceImpl(repository, businessService, userService);
  }

  @Test
  public void testGetAllTransactionByUser() throws ApiException {
    UserBean user = UserBean.builder().id(5L).login("test").firstName("test").build();
    List<Payment> founds = new ArrayList<>();
    founds.add(new Payment());
    when(userService.getUser(anyLong())).thenReturn(user);
    when(repository.findByUser(any(UserIoBuilder.class))).thenReturn(founds);
    List<PaymentBean> result = service.getAllTransactionByUser(anyLong());
    assertThat(result, IsIterableWithSize.<PaymentBean>iterableWithSize(1));
  }

  @Test
  public void testGetAmountByUser() throws ApiException {
    UserBean user = UserBean.builder().id(5L).login("test").firstName("test").build();
    List<Payment> founds = new ArrayList<>();
    Payment pay1 = new Payment();
    pay1.setUser(userMapper.mapEntity(user));
    pay1.setTransaction(252.0);
    Payment pay2 = new Payment();
    pay2.setUser(userMapper.mapEntity(user));
    pay2.setTransaction(-52.0);
    founds.add(pay1);
    founds.add(pay2);
    when(userService.getUser(anyLong())).thenReturn(user);
    when(repository.findByUser(any(UserIoBuilder.class))).thenReturn(founds);

    PaymentBean resultData = service.getAmountByUser(anyLong());
    assertThat(resultData, hasProperty("transaction", equalTo(200.0)));
  }

  @Test
  public void testCreate() throws ApiException {
    UserBean user = UserBean.builder().id(5L).login("test").firstName("test").build();
    PaymentBean input = PaymentBean.builder().id(5L).transaction(200.0).user(user).build();
    when(userService.getUser(anyLong())).thenReturn(user);
    Payment entity = new Payment();
    entity.setId(5L);
    entity.setTransaction(200.0);
    when(repository.save(any(Payment.class))).thenReturn(entity);
    PaymentBean resultData = service.createTransaction(input);
    assertThat(resultData, hasProperty("id", equalTo(5L)));
    assertThat(resultData, hasProperty("transaction", equalTo(200.0)));
  }

}
