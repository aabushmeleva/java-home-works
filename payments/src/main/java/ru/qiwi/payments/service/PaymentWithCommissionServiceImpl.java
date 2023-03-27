package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;

@Service
public class PaymentWithCommissionServiceImpl implements PaymentService {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentWithCommissionServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    @Override
    public int getTotalSum() {
        return 0;
    }
    @Override
    public int getPaymentsCount() {
        return 0;
    }
    // TODO
}
