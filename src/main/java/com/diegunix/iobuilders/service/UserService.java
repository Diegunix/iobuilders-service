package com.diegunix.iobuilders.service;

import java.util.List;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.exceptions.ApiException;

public interface UserService {

  UserBean createUser(UserBean user) throws ApiException;

  void deleteUser(Long id) throws ApiException;

  UserBean getUser(Long id) throws ApiException;

  List<UserBean> getAllUser() throws ApiException;

  UserBean updateUser(UserBean user) throws ApiException;

}
