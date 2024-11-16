package system.fullfillment.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.fullfillment.interfaces.services.ProductEntryServiceInterface;
import system.fullfillment.models.ProductEntry;
import system.fullfillment.mvc.repositories.ProductEntryJpaRepository;

import java.util.List;

@Service
public class ProductEntryService implements ProductEntryServiceInterface {

    @Autowired
    ProductEntryJpaRepository productRepository;

    @Override
    public void addNewProductEntry(String productId, String status, String fulfillmentCenter, Integer quantity, Integer value) {

    }

    @Override
    public ProductEntry getProductEntryById(Long id) {
        return null;
    }

    @Override
    public List<ProductEntry> getAllByProductId(String productId) {
        return List.of();
    }

    @Override
    public List<ProductEntry> getAllByFulfillmentCenter(String fulfillmentCenter) {
        return List.of();
    }

    @Override
    public List<ProductEntry> getAllByProductStatus(String status) {
        return List.of();
    }

    @Override
    public void deleteProductEntryById(Long id) {

    }

    @Override
    public void updateProductByColumnName(Long id, String columnName, String newValue) {

    }

    @Override
    public Integer totalSellableValue() {
        return 0;
    }

    @Override
    public Integer getAllValueForFulfillmentCenter(String fulfillmentCenter) {
        return 0;
    }
}
