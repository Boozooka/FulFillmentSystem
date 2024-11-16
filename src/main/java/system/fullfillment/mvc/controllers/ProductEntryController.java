package system.fullfillment.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.fullfillment.dto.requests.DeleteProductEntryByIdRequest;
import system.fullfillment.dto.requests.NewProductEntryRequest;
import system.fullfillment.dto.requests.UpdateProductEntryRequest;
import system.fullfillment.dto.responses.IntegerValueResponse;
import system.fullfillment.dto.responses.ProductEntryListResponse;
import system.fullfillment.exceptions.InvalidRequestException;
import system.fullfillment.interfaces.controllers.ProductEntryControllerInterface;
import system.fullfillment.models.ProductEntry;
import system.fullfillment.mvc.services.ProductEntryService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductEntryController implements ProductEntryControllerInterface {

    @Autowired
    ProductEntryService service;

    @Override
    @PostMapping("/addNewProductEntry")
    public ResponseEntity<?> addNewProductEntry(@RequestBody NewProductEntryRequest request) {
        if (request.getProductId() == null){
            throwInvalidRequestEx("Product id must not be null");
        }
        if (request.getStatus() == null){
            throwInvalidRequestEx("Status must not be null");
        }
        if (request.getFc() == null){
            throwInvalidRequestEx("Fulfillment center ('fc' column) must not be null");
        }
        if (request.getValue() <= 0){
            throwInvalidRequestEx("Value must be more than 0");
        }

        service.addNewProductEntry(request.getProductId(), request.getStatus(), request.getFc(),
                request.getQty(), request.getValue());
        return ResponseEntity.ok("Product entry successfully added");
    }

    @Override
    @PostMapping("/delete")
    public ResponseEntity<?> deleteProductEntryById(DeleteProductEntryByIdRequest request) {
        if (request.getDeletingId() <= 0){
            throwInvalidRequestEx("Invalid id");
        }

        service.deleteProductEntryById(request.getDeletingId());

        return ResponseEntity.ok("Successfully deleting Product entry");
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<?> updateProductByColumnName(UpdateProductEntryRequest request) {

        if (request.getId() == null) {
            throwInvalidRequestEx("Id must not be null");
        }
        if (request.getUpdatingColumn() == null){
            throwInvalidRequestEx("Updating column must not be null");
        }
        if (request.getNewValue() == null){
            throwInvalidRequestEx("New value must not be null");
        }

        service.updateProductByColumnName(request.getId(), request.getUpdatingColumn(), request.getNewValue());

        return ResponseEntity.ok("Successfully update Product entry");
    }

    @Override
    @GetMapping("/getAllWithProductId")
    public ResponseEntity<?> getAllByProductId(@RequestParam(name = "productID") String productId) {
        if (productId == null){
            throwInvalidRequestEx("Product id must not be null");
        }

        List<ProductEntry> result = service.getAllByProductId(productId);

        ProductEntryListResponse response = new ProductEntryListResponse(result);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/getAllWithStatus")
    public ResponseEntity<?> getAllByProductStatus(@RequestParam(name = "status") String status) {
        if (status == null){
            throwInvalidRequestEx("Status must not be null");
        }

        List<ProductEntry> result = service.getAllByProductId(status);

        ProductEntryListResponse response = new ProductEntryListResponse(result);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/getAllInCenter")
    public ResponseEntity<?> getAllByFulfillmentCenter(@RequestParam(name = "fc") String fulfillmentCenter) {
        if (fulfillmentCenter == null){
            throwInvalidRequestEx("Fulfillment center ('fc') must not be null");
        }

        List<ProductEntry> result = service.getAllByProductId(fulfillmentCenter);

        ProductEntryListResponse response = new ProductEntryListResponse(result);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/getTotalSellableValue")
    public ResponseEntity<?> totalSellableValue() {
        IntegerValueResponse responseBody = new IntegerValueResponse(service.totalSellableValue());
        return ResponseEntity.ok(responseBody);
    }

    @Override
    @GetMapping("/getTotalValueInCenter")
    public ResponseEntity<?> getAllValueForFulfillmentCenter(@RequestParam(name = "fc") String fulfillmentCenter) {
        if (fulfillmentCenter == null){
            throwInvalidRequestEx("Fulfillment center ('fc') must not be null");
        }

        IntegerValueResponse responseBody = new IntegerValueResponse(service.totalSellableValue());
        return ResponseEntity.ok(responseBody);
    }

    private void throwInvalidRequestEx(String message){
        throw new InvalidRequestException(message);
    }
}
