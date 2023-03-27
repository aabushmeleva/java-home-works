package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }
    @Override
    public int getTotalSum() {
        return Arrays.stream(paymentsDataProvider.getPayments()).mapToInt(payment -> payment.getAmount()).sum();
    }
    @Override
    public int getPaymentsCount() {
        return (int)Arrays.stream(paymentsDataProvider.getPayments()).count();
    }

}
