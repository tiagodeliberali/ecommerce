package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Data
public class CartJpa {
    @Id
    @GeneratedValue
    private UUID id;

    @Type(type="pg-uuid")
    private UUID userId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<CartItemJpa> itemList;
}
