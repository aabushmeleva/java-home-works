import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import static ru.qiwi.payments.dto.Payment.MerchantType.P2P;
import static ru.qiwi.payments.dto.Payment.MerchantType.SHOP;
import static ru.qiwi.payments.dto.Payment.Status.IN_PROGRESS;
import static ru.qiwi.payments.dto.Payment.Status.OK;
import ru.qiwi.payments.dto.TotalSum;
import ru.qiwi.payments.service.PaymentService;
import ru.qiwi.payments.service.PaymentServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
    LocalDateTime dayBeforeYesterday = LocalDateTime.now().minusDays(2);
    LocalDateTime twoDayBeforeYesterday = LocalDateTime.now().minusDays(3);
    PaymentsDataProvider paymentsDataProvider = Mockito.mock(PaymentsDataProvider.class);

    PaymentService paymentService = new PaymentServiceImpl(paymentsDataProvider);

    List<Payment> payments = Arrays.asList(
            new Payment(0, today, 10, 1, "78000000000",
                        "78000000001", SHOP, IN_PROGRESS),
            new Payment(1, yesterday, 20, 2, "78000000000",
                        "78000000001", SHOP, OK),
            new Payment(2, today.minusHours(2), 30, 1, "78000000001",
                        "78000000000", P2P, OK),
            new Payment(3, today.minusHours(3), 40, 1, "78000000002",
                        "78000000000", P2P, OK),
            new Payment(3, today.minusHours(4), 40, 1, "78000000002",
                        "78000000003", P2P, OK),
            new Payment(1, twoDayBeforeYesterday, 20, 2, "78000000010",
                        "78000000004", SHOP, OK),
            new Payment(1, yesterday, 20, 2, "78000000010",
                        "78000000004", P2P, OK),
            new Payment(1, dayBeforeYesterday, 20, 2, "78000000010",
                        "78000000006", P2P, OK),
            new Payment(1, dayBeforeYesterday, 20, 2, "78000000010",
                        "78000000004", SHOP, IN_PROGRESS),
            new Payment(1, today.minusHours(5), 20, 2, "78000000010",
                        "78000000004", SHOP, OK)
    );

    @Test
    void getTotalSumTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getTotalSum("78000000001"),
                                new TotalSum("78000000001", 22, 31));
    }
    @Test
    void getPaymentsCountTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getPaymentsCount("78000000001", OK, SHOP),
                                0);
        Assertions.assertEquals(paymentService.getPaymentsCount("78000000000", OK, SHOP),
                                1);
        Assertions.assertEquals(paymentService.getPaymentsCount("78000000002", OK, P2P),
                                2);
    }

    @Test
    void getAllReplenishmentsTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getAllReplenishments("78000000004").stream().count(),
                                3);
        Assertions.assertEquals(
                paymentService.getAllReplenishments("78000000004").stream()
                        .findFirst()
                        .get()
                        .getDate()
                        .toLocalDate(),
                LocalDateTime.now().minusDays(3).toLocalDate());
        Assertions.assertEquals(
                paymentService.getAllReplenishments("78000000004").stream()
                        .skip(2)
                        .findFirst()
                        .get()
                        .getDate()
                        .toLocalDate(),
                LocalDateTime.now().toLocalDate());
    }

    @Test
    void getPaymentsTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getPayments("78000000010", P2P, dayBeforeYesterday, today).stream().count(),
                                2);
        Assertions.assertEquals(paymentService.getPayments("78000000010", SHOP, dayBeforeYesterday, today).stream().count(),
                                1);
        Assertions.assertEquals(paymentService.getPayments("78000000010", P2P, dayBeforeYesterday, today).stream().findFirst().get().getToAccount(),
                                "78000000006");
    }
    @Test
    void getNRTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getNR(yesterday, today, P2P),
                                5);
        Assertions.assertEquals(paymentService.getNR(twoDayBeforeYesterday, dayBeforeYesterday, SHOP),
                                2);
    }

    @Test
    void getRevenueTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getRevenue(dayBeforeYesterday, yesterday, P2P), 22);
        Assertions.assertEquals(paymentService.getRevenue(twoDayBeforeYesterday, dayBeforeYesterday, SHOP),
                                22);
    }

    @Test
    void getTopUsersTest() {
        Mockito.when(paymentsDataProvider.getPayments())
                .thenReturn(payments);
        Assertions.assertEquals(paymentService.getTopUsers(yesterday, today).stream().count(), 4 );
        Assertions.assertEquals(paymentService.getTopUsers(yesterday, today).stream().findFirst().get(),
                                "78000000002");

        List<String> exp = paymentService.getTopUsers(yesterday, today);
        Assertions.assertEquals(exp.get(exp.size()-1), "78000000000");
    }

}
