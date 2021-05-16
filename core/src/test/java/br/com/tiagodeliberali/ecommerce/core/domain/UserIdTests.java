package br.com.tiagodeliberali.ecommerce.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserIdTests {
    @Test
    void user_id_cannot_accept_null_uuid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserId(null));
    }
}
