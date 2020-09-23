package com.diegunix.iobuilders.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.error.Error;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.commons.exceptions.ApiResourceNotExitsException;
import com.diegunix.iobuilders.commons.mappers.UserMapper;
import com.diegunix.iobuilders.dao.domain.UserIoBuilder;
import com.diegunix.iobuilders.dao.repository.UserRepository;
import com.diegunix.iobuilders.service.UserService;
import com.diegunix.iobuilders.service.business.UserBusinessService;

@Service
public class UserServiceImpl implements UserService {

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  private UserRepository userRepository;
  private UserBusinessService userBusiness;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserBusinessService userBusiness) {
    this.userRepository = userRepository;
    this.userBusiness = userBusiness;
  }

  @Transactional
  @Override
  public UserBean createUser(UserBean user) throws ApiException {
    userBusiness.validation(user);
    user.setCreatedDate(LocalDateTime.now());
    user.setActive(true);
    UserIoBuilder saved = userRepository.save(userMapper.mapEntity(user));
    return userMapper.mapEntity(saved);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) throws ApiException {
    UserBean user = getUser(id);
    user.setUpdatedDate(LocalDateTime.now());
    user.setActive(false);
    userRepository.save(userMapper.mapEntity(user));
  }

  @Override
  public UserBean getUser(Long id) throws ApiException {
    Optional<UserIoBuilder> optionalApp = userRepository.findById(id);
    if (optionalApp.isPresent()) {
      UserBean bean = userMapper.mapEntity(optionalApp.get());
      return bean;
    }
    throw new ApiResourceNotExitsException(Error.API_ENTITY_NOT_FOUND);
  }

  @Override
  public List<UserBean> getAllUser() {
    return userMapper.mapEntity(userRepository.findAll());
  }

  @Override
  public UserBean updateUser(UserBean user) throws ApiException {
    userBusiness.validation(user);
    UserBean updated = getUser(user.getId());
    updated.setUpdatedDate(LocalDateTime.now());
    updated.setMail(user.getMail());
    updated.setLastName(user.getLastName());
    updated.setFirstName(user.getFirstName());

    UserIoBuilder saved = userRepository.save(userMapper.mapEntity(updated));
    return userMapper.mapEntity(saved);
  }

}
