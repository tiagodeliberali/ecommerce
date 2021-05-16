package br.com.tiagodeliberali.ecommerce.core.application.service;

import br.com.tiagodeliberali.ecommerce.core.application.port.in.GetCartQuery;
import br.com.tiagodeliberali.ecommerce.core.application.port.out.LoadCartPort;
import br.com.tiagodeliberali.ecommerce.core.domain.Cart;
import br.com.tiagodeliberali.ecommerce.core.domain.UserId;
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
