package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;

@Service
public class PaymentServiceImpl extends PaymentAbstractServiceImpl<Payment> {
    private PaymentsDataProvider paymentsDataProvider;
    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
        this.paymentsArray = this.paymentsDataProvider::getPayments;
        this.totalSumFunction = Payment::getTotalSum;
    }
}
