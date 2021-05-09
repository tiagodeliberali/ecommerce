package br.com.tiagodeliberali.ecommerce.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataStoreRepository extends JpaRepository<CartJpa, String> {

    @Query("select c from CartJpa c where userId = :userId")
    Optional<CartJpa> findActiveCartByUser(UUID userId);
}
