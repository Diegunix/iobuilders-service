package com.diegunix.iobuilders.commons.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.controller.dto.UserDto;
import com.diegunix.iobuilders.dao.domain.UserIoBuilder;

@Mapper
public interface UserMapper {

  UserDto map(UserBean bean);

  UserBean map(UserDto dto);

  @IterableMapping(qualifiedByName = { "defaultMapper" })
  List<UserDto> map(Iterable<UserBean> content);

  UserBean mapEntity(UserIoBuilder entity);

  UserIoBuilder mapEntity(UserBean bean);

  @IterableMapping(qualifiedByName = { "defaultMapper" })
  List<UserBean> mapEntity(Iterable<UserIoBuilder> content);

}