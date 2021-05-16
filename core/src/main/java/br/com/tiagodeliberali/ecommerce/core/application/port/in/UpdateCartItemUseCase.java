package br.com.tiagodeliberali.ecommerce.core.application.port.in;

import br.com.tiagodeliberali.ecommerce.core.application.port.out.ProductNotFoundException;
import br.com.tiagodeliberali.ecommerce.core.domain.ProductId;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;

public interface UpdateCartItemUseCase {
    void addItemToCart(UserId userId, ProductId productId, int quantity) throws ProductNotFoundException;
}
