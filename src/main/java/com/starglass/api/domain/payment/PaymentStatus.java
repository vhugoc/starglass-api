package com.starglass.api.domain.payment;

public enum PaymentStatus {

    SUCCEEDED("succeeded"),

    PENDING("pending"),

    FAILED("failed"),

    CANCELED("canceled"),

    REFUNDED("refunded"),

    PARTIALLY_REFUNDED("partially_refunded"),

    DISPUTED("disputed"),

    INCOMPLETE("incomplete"),

    PROCESSING("processing");

    private final String status;

    private PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompleted() {
        return this == SUCCEEDED;
    }

}
