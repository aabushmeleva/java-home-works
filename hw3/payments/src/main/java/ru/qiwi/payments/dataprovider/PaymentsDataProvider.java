package ru.qiwi.payments.dataprovider;

import org.springframework.stereotype.Component;
import ru.qiwi.payments.dto.Payment;
import static ru.qiwi.payments.dto.Payment.MerchantType.P2P;
import static ru.qiwi.payments.dto.Payment.MerchantType.SHOP;
import static ru.qiwi.payments.dto.Payment.Status.OK;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PaymentsDataProvider {
    public List<Payment> getPayments() {
        return Arrays.asList(
                new Payment(0, LocalDateTime.now(), 10, 1, "78000000000",
                            "78000000001", SHOP, OK),
                new Payment(1, LocalDateTime.now(), 20, 2, "78000000000",
                            "78000000001", SHOP, OK),
                new Payment(2, LocalDateTime.now(), 30, 1, "78000000002",
                            "78000000000", P2P, OK),
                new Payment(3, LocalDateTime.now(), 40, 1, "78000000002",
                            "78000000000", P2P, OK)
        );
    }
}
