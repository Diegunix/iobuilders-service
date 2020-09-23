package com.diegunix.iobuilders.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegunix.iobuilders.dao.domain.UserIoBuilder;

public interface UserRepository extends JpaRepository<UserIoBuilder, Long> {

}
