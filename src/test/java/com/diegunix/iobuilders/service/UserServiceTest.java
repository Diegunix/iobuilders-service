package com.diegunix.iobuilders.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.dao.domain.UserIoBuilder;
import com.diegunix.iobuilders.dao.repository.UserRepository;
import com.diegunix.iobuilders.service.business.UserBusinessService;
import com.diegunix.iobuilders.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  UserService service;

  private UserRepository repository;
  private UserBusinessService businessService;

  @Before
  public void prepareEnvironment() {
    repository = mock(UserRepository.class);
    businessService = new UserBusinessService();
    service = new UserServiceImpl(repository, businessService);
  }

  @Test
  public void testGetAll() throws ApiException {
    List<UserIoBuilder> founds = new ArrayList<>();
    founds.add(new UserIoBuilder());
    when(repository.findAll()).thenReturn(founds);
    List<UserBean> result = service.getAllUser();
    assertThat(result, IsIterableWithSize.<UserBean>iterableWithSize(1));
  }

  @Test
  public void testGetOne() throws ApiException {
    UserIoBuilder entity = new UserIoBuilder();
    entity.setId(5L);
    entity.setLogin("description");
    when(repository.findById(any())).thenReturn(Optional.of(entity));
    UserBean resultData = service.getUser(anyLong());
    assertThat(resultData, hasProperty("id", equalTo(5L)));
  }

  @Test
  public void testDelete() throws ApiException {
    UserIoBuilder entity = new UserIoBuilder();
    entity.setId(5L);
    entity.setLogin("description");
    when(repository.findById(any())).thenReturn(Optional.of(entity));
    service.deleteUser(anyLong());
    Mockito.verify(repository, Mockito.times(1)).save(any(UserIoBuilder.class));
  }

  @Test
  public void testUpdate() throws ApiException {
    UserBean input = UserBean.builder().id(5L).login("description").mail("test@test.com").firstName("test")
        .lastName("test").createdDate(LocalDateTime.now()).build();
    UserIoBuilder entity = new UserIoBuilder();
    entity.setId(5L);
    entity.setLogin("description");
    when(repository.findById(any())).thenReturn(Optional.of(entity));
    when(repository.save(any(UserIoBuilder.class))).thenReturn(entity);
    UserBean resultData = service.updateUser(input);
    assertThat(resultData, hasProperty("login", equalTo("description")));
  }

  @Test
  public void testCreate() throws ApiException {
    UserBean input = UserBean.builder().id(5L).login("description").mail("test@test.com").firstName("test")
        .lastName("test").createdDate(LocalDateTime.now()).build();
    UserIoBuilder entity = new UserIoBuilder();
    entity.setId(5L);
    entity.setLogin("description");
    when(repository.save(any(UserIoBuilder.class))).thenReturn(entity);
    UserBean resultData = service.createUser(input);
    assertThat(resultData, hasProperty("id", equalTo(5L)));
    assertThat(resultData, hasProperty("login", equalTo("description")));
  }

}
