package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;
import java.util.Arrays;

@Service
public class PaymentWithCommissionServiceImpl implements PaymentService {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    @Override
    public int getTotalSum() {
        return Arrays.stream(paymentsDataProvider.getPaymentWithCommission()).mapToInt(payment -> payment.getAmount() + payment.getCommission()).sum();
    }
    @Override
    public int getPaymentsCount() {
        return (int)Arrays.stream(paymentsDataProvider.getPaymentWithCommission()).count();
    }
}
