package ru.qiwi.payments.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.PaymentWithCommission;

public class PaymentWithCommissionServiceTest {

    PaymentsDataProvider paymentsDataProvider = Mockito.mock(PaymentsDataProvider.class);

    PaymentService paymentService = new PaymentWithCommissionServiceImpl(paymentsDataProvider);

    @Test
    void getZeroSumTest() {
        Mockito.when(paymentsDataProvider.getPaymentWithCommission()).thenReturn(new PaymentWithCommission[]{});
        Assertions.assertEquals(paymentService.getTotalSum(), 0);
    }

    @Test
    void getSumTest() {
        Mockito.when(paymentsDataProvider.getPaymentWithCommission())
                .thenReturn(new PaymentWithCommission[]{
                        new PaymentWithCommission(1, 1),
                        new PaymentWithCommission(4, 1)
                });
        Assertions.assertEquals(paymentService.getTotalSum(), 7);
    }

    @Test
    void getCountTest() {
        Mockito.when(paymentsDataProvider.getPaymentWithCommission())
                .thenReturn(new PaymentWithCommission[]{
                        new PaymentWithCommission(1, 1),
                        new PaymentWithCommission(4, 1)
                });
        Assertions.assertEquals(paymentService.getPaymentsCount(), 2);
    }
}
