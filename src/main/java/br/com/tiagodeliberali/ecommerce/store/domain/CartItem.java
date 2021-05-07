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
}
