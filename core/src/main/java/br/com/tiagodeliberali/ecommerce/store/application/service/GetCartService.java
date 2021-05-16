package br.com.tiagodeliberali.ecommerce.store.application.service;

import br.com.tiagodeliberali.ecommerce.store.application.port.in.GetCartQuery;
import br.com.tiagodeliberali.ecommerce.store.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.store.domain.Cart;
import br.com.tiagodeliberali.ecommerce.store.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCartService implements GetCartQuery {
    private final LoadCartPort loadCartPort;

    @Autowired
    public GetCartService(LoadCartPort loadCartPort) {
        this.loadCartPort = loadCartPort;
    }

    @Override
    public Cart getActiveCart(UserId userId) {
        return loadCartPort.getActiveCart(userId);
    }
}
