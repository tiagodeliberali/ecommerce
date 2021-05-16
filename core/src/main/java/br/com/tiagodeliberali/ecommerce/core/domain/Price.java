package br.com.tiagodeliberali.ecommerce.core.domain;

import java.util.Arrays;

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
        return Arrays.asList("usd", "brl").contains(currency);
    }

    public Price add(Price price) {
        if (!this.currency.equals(price.currency)) {
            throw new IllegalArgumentException("Price currency must be the same");
        }

        return new Price(this.amount + price.amount(), this.currency);
    }

    public Price times(int value) {
        return new Price(this.amount * value, this.currency);
    }
}
