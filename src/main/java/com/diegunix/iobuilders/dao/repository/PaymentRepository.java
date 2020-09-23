package com.diegunix.iobuilders.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegunix.iobuilders.dao.domain.Payment;
import com.diegunix.iobuilders.dao.domain.UserIoBuilder;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  List<Payment> findByUser(UserIoBuilder user);

}
