package ru.qiwi.payments.service;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public abstract class PaymentAbstractServiceImpl<T> implements PaymentService {
    public Supplier<T[]> paymentsArray;
    public ToIntFunction<T> totalSumFunction;

    public int getTotalSum() {
        return Arrays.stream(paymentsArray.get())
                .mapToInt(totalSumFunction)
                .sum();
    }

    public int getPaymentsCount() {
        return (int) Arrays.stream(paymentsArray.get()).count();
    }

}

