package ru.qiwi.payments.service;

import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.TotalSum;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {

    TotalSum getTotalSum(String personId);
    int getPaymentsCount(String personId, Payment.Status status, Payment.MerchantType merchantType);
    List<Payment> getAllReplenishments(String personId);
    List<Payment> getPayments(String personId, Payment.MerchantType merchantType, LocalDateTime dateFrom, LocalDateTime dateTill);
    double getNR(LocalDateTime dateFrom, LocalDateTime dateTill, Payment.MerchantType merchantType);
    double getRevenue(LocalDateTime dateFrom, LocalDateTime dateTill, Payment.MerchantType merchantType);
    List<String> getTopUsers(LocalDateTime dateFrom, LocalDateTime dateTill);
}
