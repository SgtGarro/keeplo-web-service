package com.acme.keeplo.platform.subscription.domain.model.valueObjects;

public record CardNumber(String value) {
    public CardNumber {
        if (value == null || !value.matches("\\d{16}")) {
            throw new IllegalArgumentException("Invalid card number");
        }
    }
}