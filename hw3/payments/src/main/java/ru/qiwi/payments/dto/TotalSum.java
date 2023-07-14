package ru.qiwi.payments.dto;

public class TotalSum {
    private final String personId;

    private final double incomingSum;

    private final double outgoingSum;

    public TotalSum(String personId, double incomingSum, double outgoingSum) {
        this.personId = personId;
        this.incomingSum = incomingSum;
        this.outgoingSum = outgoingSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TotalSum totSm = (TotalSum) o;
        if (personId == totSm.personId &&
                incomingSum == totSm.incomingSum &&
                outgoingSum == totSm.outgoingSum)
            return true;
        else
            return false;
    }
}
