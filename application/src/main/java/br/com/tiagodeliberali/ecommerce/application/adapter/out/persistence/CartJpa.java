package br.com.tiagodeliberali.ecommerce.application.adapter.out.persistence;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartJpa {
    @Id
    @GeneratedValue
    private UUID id;

    @Type(type = "pg-uuid")
    private UUID userId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<CartItemJpa> itemList;
}
