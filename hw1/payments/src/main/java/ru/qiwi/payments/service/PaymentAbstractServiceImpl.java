package ru.qiwi.payments.service;

import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;
import java.util.Arrays;

public abstract class PaymentAbstractServiceImpl implements PaymentService {

    public int getTotalSum(Payment[] payments) {
        return Arrays.stream(payments).mapToInt(payment -> payment.getTotalSum()).sum();
    }

    public int getPaymentsCount(Payment[] payments) {
        return (int) Arrays.stream(payments).count();
    }

}
