package com.payments.paymentprocessor.repository;

import javax.servlet.http.HttpServletRequest;

public interface IPRequestRepository {
    String getClientIP(HttpServletRequest httpServletRequest);
}
