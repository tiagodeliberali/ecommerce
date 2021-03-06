package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductJpa {
    @Id
    @GeneratedValue
    private UUID id;

    private float price;

    private String currency;

    public static ProductJpa build(float price, String currency) {
        ProductJpa product = new ProductJpa();
        product.price = price;
        product.currency = currency;

        return product;
    }
}
