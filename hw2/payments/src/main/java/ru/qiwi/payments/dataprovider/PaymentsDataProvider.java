package ru.qiwi.payments.dataprovider;

import org.springframework.stereotype.Component;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.PaymentWithCommission;

@Component
public class PaymentsDataProvider {
    public Payment[] getPayments() {
        return new Payment[] {
                new Payment(1),
                new Payment(4)
        };
    }

    public PaymentWithCommission[] getPaymentWithCommission() {
        return new PaymentWithCommission[] {
                new PaymentWithCommission(1, 1),
                new PaymentWithCommission(4, 1)
        };
    }
}
