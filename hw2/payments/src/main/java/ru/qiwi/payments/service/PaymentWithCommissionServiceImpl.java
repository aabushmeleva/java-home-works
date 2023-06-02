package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.PaymentWithCommission;

@Service
public class PaymentWithCommissionServiceImpl extends PaymentAbstractServiceImpl<PaymentWithCommission> {
    private PaymentsDataProvider paymentsDataProvider;
    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
        this.paymentsArray = this.paymentsDataProvider::getPaymentWithCommission;
        this.totalSumFunction = PaymentWithCommission::getTotalSum;
    }
}