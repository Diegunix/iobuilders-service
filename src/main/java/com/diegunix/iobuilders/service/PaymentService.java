package com.diegunix.iobuilders.service;

import java.util.List;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.commons.exceptions.ApiException;

public interface PaymentService {

  PaymentBean createTransaction(PaymentBean transaction) throws ApiException;

  PaymentBean getAmountByUser(Long id) throws ApiException;

  List<PaymentBean> getAllTransactionByUser(Long id) throws ApiException;

}
