package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PriceTests {
    @Test
    void price_with_same_value_and_currency_are_equal() {
        Price p1 = new Price(15.22f, "usd");
        Price p2 = new Price(15.22f, "usd");

        assertThat(p1).isEqualTo(p2);
    }

    @Test
    void price_cannot_be_negative_number() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Price.ofDollar(-1);
        });
    }

    @Test
    void price_must_have_at_most_two_decimals() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Price.ofDollar(1.999f);
        });
    }

    @Test
    void price_currency_must_be_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Price(1.99f, "xyz");
        });
    }

    @Test
    void price_do_not_accept_null_currency() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Price(1.99f, null);
        });
    }

    @Test
    void add_two_prices_sums_its_amounts() {
        Price p1 = Price.ofDollar(30);
        Price p2 = Price.ofDollar(20);

        Price total = p1.add(p2);

        assertThat(total).isEqualTo(Price.ofDollar(50));
    }

    @Test
    void add_prices_must_have_same_currency() {
        Price p1 = Price.ofDollar(30);
        Price p2 = new Price(20, "brl");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            p1.add(p2);
        });
    }

    @Test
    void multiply_price_by_values_keep_price_currency() {
        Price price = Price.ofDollar(30);

        Price total = price.times(3);

        assertThat(total).isEqualTo(Price.ofDollar(90));
    }
}
