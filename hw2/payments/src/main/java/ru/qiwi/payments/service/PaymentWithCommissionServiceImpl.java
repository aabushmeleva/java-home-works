package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;

@Service
public class PaymentWithCommissionServiceImpl extends PaymentAbstractServiceImpl {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    @Override
    public int getTotalSum() {
        return super.getTotalSum(paymentsDataProvider.getPaymentWithCommission());
    }
    @Override
    public int getPaymentsCount() {
        return super.getPaymentsCount(paymentsDataProvider.getPaymentWithCommission());
    }
}