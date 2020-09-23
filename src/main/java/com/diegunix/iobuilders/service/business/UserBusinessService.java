package com.diegunix.iobuilders.service.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.commons.error.Error;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.commons.exceptions.ApiValidationException;

@Service
public class UserBusinessService {

  @Autowired
  public UserBusinessService() {
  }

  public void validation(UserBean user) throws ApiException {
    if (!EmailValidator.getInstance().isValid(user.getMail())) {
      throw new ApiValidationException(Error.API_USER_MAIL_INCORRECT);
    }
    if (StringUtils.isBlank(user.getFirstName())) {
      throw new ApiValidationException(Error.API_USER_NAME_INCORRECT);
    }
    if (StringUtils.isBlank(user.getLastName())) {
      throw new ApiValidationException(Error.API_USER_NAME_INCORRECT);
    }
    if (StringUtils.isBlank(user.getLogin())) {
      throw new ApiValidationException(Error.API_USER_USERNAME_INCORRECT);
    }
  }

}
