package system.fullfillment.interfaces.services;

import system.fullfillment.models.ProductEntry;

import java.util.List;

public interface ProductEntryServiceInterface {
    void addNewProductEntry(String productId, String status, String fulfillmentCenter,
                            Integer quantity, Integer value);
    ProductEntry getProductEntryById(Long id);
    List<ProductEntry> getAllByProductId(String productId);
    List<ProductEntry> getAllByFulfillmentCenter(String fulfillmentCenter);
    List<ProductEntry> getAllByProductStatus(String status);
    void deleteProductEntryById(Long id);
    void updateProductByColumnName(Long id, String columnName, String newValue);
    Integer totalSellableValue();
    Integer getAllValueForFulfillmentCenter(String fulfillmentCenter);
}
