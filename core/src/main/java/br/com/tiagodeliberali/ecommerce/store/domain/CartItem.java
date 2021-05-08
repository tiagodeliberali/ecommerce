package br.com.tiagodeliberali.ecommerce.store.domain;

public record CartItem(Product product, int quantity) {
    public CartItem {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive int");
        }
    }

    public Price getTotal() {
        return product.getValue().times(quantity);
    }

    public CartItem add(CartItem cartItem) {
        if (!this.product.equals(cartItem.product)) {
            throw new IllegalArgumentException("Product of both items must be the same");
        }

        return new CartItem(product, quantity + cartItem.quantity());
    }
}
