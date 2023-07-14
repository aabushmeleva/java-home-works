package ru.qiwi.payments.service;

import org.springframework.stereotype.Service;
import ru.qiwi.payments.dataprovider.PaymentsDataProvider;
import ru.qiwi.payments.dto.Payment;
import ru.qiwi.payments.dto.TotalSum;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentsDataProvider paymentsDataProvider;

    public PaymentServiceImpl(PaymentsDataProvider paymentsDataProvider) {
        this.paymentsDataProvider = paymentsDataProvider;
    }

    // должен вернуть объект TotalSum, который содержит сумму всех списаний
    // и сумму всех пополнений для пользователя с personId
    public TotalSum getTotalSum(String personId) {
        Map<Boolean, Optional<Double>> totalAmount = paymentsDataProvider.getPayments().stream()
                .filter(payments -> (payments.getFromAccount() == personId) || payments.getToAccount() == personId)
                .filter(payments -> payments.getStatus() == Payment.Status.OK)
                .collect(Collectors.partitioningBy(
                        payments -> (payments.getFromAccount() == personId),
                        Collectors.mapping(Payment::getTotalSum, Collectors.reducing((x, y) -> (x + y))))
                );
        double incomingSum = totalAmount.get(false).orElse((double) 0);
        double outgoingSum = totalAmount.get(true).orElse((double) 0);
        return new TotalSum(personId, incomingSum, outgoingSum);
    }

    // должен вернуть количество платежей, совершенных пользователем
    // фильтры на статус платежа и тип мерчанта
    public int getPaymentsCount(String personId, Payment.Status status, Payment.MerchantType merchantType) {
        return (int) paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                payment.getFromAccount() == personId &&
                                        payment.getStatus() == status &&
                                        payment.getMerchantType() == merchantType
                )
                .count();
    }

    // все пополнения пользователя(сортировка по дате)
    public List<Payment> getAllReplenishments(String personId) {
        return paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                payment.getToAccount() == personId &&
                                        payment.getStatus() == Payment.Status.OK)
                .sorted(Comparator.comparing(o -> o.getDate()))
                .collect(Collectors.toList());
    }

    // все платежи пользователя за период(сортировка по дате)
    public List<Payment> getPayments(String personId, Payment.MerchantType merchantType, LocalDateTime dateFrom, LocalDateTime dateTill) {
        return paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                payment.getFromAccount() == personId &&
                                        payment.getMerchantType() == merchantType &&
                                        payment.getStatus() == Payment.Status.OK &&
                                        (payment.getDate().isAfter(dateFrom) || payment.getDate().isEqual(dateFrom)) &&
                                        (payment.getDate().isBefore(dateTill))
                )
                .sorted(Comparator.comparing(o -> o.getDate()))
                .collect(Collectors.toList());
    }

    // NR(сумма комиссий) за период по мерчанту
    public double getNR(LocalDateTime dateFrom, LocalDateTime dateTill, Payment.MerchantType merchantType) {
        return paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                payment.getMerchantType() == merchantType &&
                                        (payment.getDate().isAfter(dateFrom) || payment.getDate().isEqual(dateFrom)) &&
                                        (payment.getDate().isBefore(dateTill))
                )
                .collect(Collectors.mapping(Payment::getCommission, Collectors.reducing((x, y) -> (x + y))))
                .orElse((double) 0);
    }

    // Оборот за период по мерчанту
    public double getRevenue(LocalDateTime dateFrom, LocalDateTime dateTill, Payment.MerchantType merchantType) {
        return paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                payment.getMerchantType() == merchantType &&
                                        (payment.getDate().isAfter(dateFrom) || payment.getDate().isEqual(dateFrom)) &&
                                        (payment.getDate().isBefore(dateTill))
                )
                .collect(Collectors.mapping(Payment::getTotalSum, Collectors.reducing((x, y) -> (x + y))))
                .orElse((double) 0);
    }

    // топ 10 пользователей по обороту(оборот = сумма списаний) за период, сортированый список
    public List<String> getTopUsers(LocalDateTime dateFrom, LocalDateTime dateTill) {
        return paymentsDataProvider.getPayments().stream()
                .filter(payment ->
                                (payment.getDate().isAfter(dateFrom) || payment.getDate().isEqual(dateFrom)) &&
                                        (payment.getDate().isBefore(dateTill))
                )
                .collect(Collectors.groupingBy(Payment::getFromAccount,
                                               Collectors.summingDouble(Payment::getTotalSum)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
