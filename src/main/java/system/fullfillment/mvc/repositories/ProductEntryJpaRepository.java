package system.fullfillment.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.fullfillment.models.ProductEntry;

import java.util.List;

@Repository
public interface ProductEntryJpaRepository extends JpaRepository<ProductEntry, Long> {
    List<ProductEntry> findAllByProductId(String productEntry);
    List<ProductEntry> findAllByFulfillmentCenter(String fulfillmentCenter);
    List<ProductEntry> findAllByStatus(String status);
}
