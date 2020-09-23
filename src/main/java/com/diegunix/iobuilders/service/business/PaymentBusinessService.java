package com.diegunix.iobuilders.service.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.commons.error.Error;
import com.diegunix.iobuilders.commons.exceptions.ApiException;
import com.diegunix.iobuilders.commons.exceptions.ApiValidationException;

@Service
public class PaymentBusinessService {

  @Autowired
  public PaymentBusinessService() {
  }

  public void validation(PaymentBean transaction) throws ApiException {
    if (transaction.getTransaction() == 0.0) {
      throw new ApiValidationException(Error.API_TRANSACTION_VALIDATE_INCORRECT);
    }
  }

  public PaymentBean getAmount(List<PaymentBean> transactions) throws ApiValidationException {
    PaymentBean transaction = transactions.stream().findFirst()
        .orElseThrow(() -> new ApiValidationException(Error.API_TRANSACTION_VALIDATE_INCORRECT));
    transaction.setTransaction(transactions.stream().collect(Collectors.summingDouble(PaymentBean::getTransaction)));
    return transaction;
  }

}
