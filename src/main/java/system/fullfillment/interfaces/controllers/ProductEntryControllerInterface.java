package system.fullfillment.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import system.fullfillment.dto.requests.DeleteProductEntryByIdRequest;
import system.fullfillment.dto.requests.NewProductEntryRequest;
import system.fullfillment.dto.requests.UpdateProductEntryRequest;

public interface ProductEntryControllerInterface {
    ResponseEntity<?> addNewProductEntry(NewProductEntryRequest request);
    ResponseEntity<?> deleteProductEntryById(DeleteProductEntryByIdRequest request);
    ResponseEntity<?> updateProductByColumnName(UpdateProductEntryRequest request);
    ResponseEntity<?> getAllByProductId(String productId);
    ResponseEntity<?> getAllByProductStatus(String status);
    ResponseEntity<?> getAllByFulfillmentCenter(String fulfillmentCenter);
    ResponseEntity<?> totalSellableValue();
    ResponseEntity<?> getAllValueForFulfillmentCenter(String fulfillmentCenter);
    ResponseEntity<?> getById(Long id);
}
