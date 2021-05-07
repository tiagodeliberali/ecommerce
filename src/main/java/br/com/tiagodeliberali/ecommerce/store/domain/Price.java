package br.com.tiagodeliberali.ecommerce.store.domain;

import java.util.Objects;

public record Price(float amount, String currency) {
    public static Price ZERO = Price.ofDollar(0);

    public static Price ofDollar(float amount) {
        return new Price(amount, "usd");
    }

    public Price {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount should always be non negative");
        }

        if (Math.round(amount * 100) != amount * 100) {
            throw new IllegalArgumentException("Amount should have at most two decimals");
        }

        if (!isValidCurrency(currency)) {
            throw new IllegalArgumentException("Invalid currency");
        }
    }

    private boolean isValidCurrency(String currency) {
        return "usd".equals(currency);
    }
}
