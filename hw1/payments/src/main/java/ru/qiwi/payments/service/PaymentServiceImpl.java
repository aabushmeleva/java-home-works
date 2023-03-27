package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }
    @Override
    public int getTotalSum() {
        return super.getTotalSum(paymentsDataProvider.getPayments());
    }
    @Override
    public int getPaymentsCount() {
        return super.getPaymentsCount(paymentsDataProvider.getPayments());
    }

    // TODO
}
