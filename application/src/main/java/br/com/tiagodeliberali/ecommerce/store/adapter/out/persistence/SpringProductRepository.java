package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SpringProductRepository extends JpaRepository<ProductJpa, String>  {

    @Query("select p from ProductJpa p where id = :productId")
    Optional<ProductJpa> findById(UUID productId);
}
