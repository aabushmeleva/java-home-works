package ru.qiwi.payments.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;

public class PaymentServiceTest {

    PaymentsDataProvider paymentsDataProvider = Mockito.mock(PaymentsDataProvider.class);

    PaymentService paymentService = new PaymentServiceImpl(paymentsDataProvider);

    @Test
    void getSumZeroTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(new Payment[]{});
        Assertions.assertEquals(paymentService.getTotalSum(), 0);
    }

    @Test
    void getSumTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(new Payment[]{
                        new Payment(1),
                        new Payment(4)
                });
        Assertions.assertEquals(paymentService.getTotalSum(), 5);
    }

    @Test
    void getSumWithCommisionTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(new Payment[]{
                        new Payment(1),
                        new Payment(4)
                });
        Assertions.assertEquals(paymentService.getTotalSum(), 5);
    }

    @Test
    void getCountTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(new Payment[]{
                        new Payment(1),
                        new Payment(4)
                });
        Assertions.assertEquals(paymentService.getPaymentsCount(), 2);
    }
}