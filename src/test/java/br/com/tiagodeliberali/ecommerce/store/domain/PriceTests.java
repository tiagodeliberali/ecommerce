package br.com.tiagodeliberali.ecommerce.store.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PriceTests {
    @Test
    public void price_with_same_value_and_currency_are_equal() {
        Price p1 = new Price(15.22f, "usd");
        Price p2 = new Price(15.22f, "usd");

        assertThat(p1).isEqualTo(p2);
    }

    @Test
    public void price_cannot_be_negative_number() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Price.ofDollar(-1);
        });
    }

    @Test
    public void price_must_have_at_most_two_decimals() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Price.ofDollar(1.999f);
        });
    }

    @Test
    public void price_currency_must_be_valid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Price(1.99f, "brl");
        });
    }

    @Test
    public void price_do_not_accept_null_currency() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Price(1.99f, null);
        });
    }
}
