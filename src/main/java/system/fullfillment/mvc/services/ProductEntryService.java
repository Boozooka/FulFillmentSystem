package system.fullfillment.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import system.fullfillment.exceptions.InvalidRequestException;
import system.fullfillment.exceptions.NotFoundProductEntryException;
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
        ProductEntry entry = new ProductEntry();
        entry.setProductId(productId);
        entry.setStatus(status);
        entry.setFulfillmentCenter(fulfillmentCenter);
        entry.setQuantity(quantity);
        entry.setValue(value);

        productRepository.save(entry);
    }

    @Override
    public ProductEntry getProductEntryById(Long id) {
        if (productRepository.findById(id).isEmpty()){
            throwNotFoundProductEntry("Id = " + id);
        }
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<ProductEntry> getAllByProductId(String productId) {
        return productRepository.findAllByProductId(productId);
    }

    @Override
    public List<ProductEntry> getAllByFulfillmentCenter(String fulfillmentCenter) {
        return productRepository.findAllByFulfillmentCenter(fulfillmentCenter);
    }

    @Override
    public List<ProductEntry> getAllByProductStatus(String status) {
        return productRepository.findAllByStatus(status);
    }

    @Override
    public void deleteProductEntryById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){
            throwNotFoundProductEntry("Id = " + id);
        }
    }

    @Override
    public void updateProductByColumnName(Long id, String columnName, String newValue) {
        if (productRepository.findById(id).isEmpty()){
            throwNotFoundProductEntry("Id = " + id);
        }
        ProductEntry updatingEntity = productRepository.getReferenceById(id);

        switch (columnName){
            case "status" -> updatingEntity.setStatus(newValue);
            case "fc" -> updatingEntity.setFulfillmentCenter(newValue);
            case "qty" -> {
                try {
                    updatingEntity.setQuantity(Integer.valueOf(newValue));
                } catch (NumberFormatException ex){
                    throw new InvalidRequestException("Quantity must be number, not '" + newValue + "'");
                }
            }
            case "val" -> {
                try {
                    updatingEntity.setValue(Integer.valueOf(newValue));
                } catch (NumberFormatException ex){
                    throw new InvalidRequestException("Value must be number, not '" + newValue + "'");
                }
            }
            default -> throw new InvalidRequestException("Product entries doesn't have '" + columnName +
                    "' column");
        }
        productRepository.save(updatingEntity);
    }

    @Override
    public Integer totalSellableValue() {
        Integer result = 0;
        List<ProductEntry> sellable = getAllByProductStatus("sellable");

        for (int i = 0; i < sellable.size(); i++){
            ProductEntry current = sellable.get(i);
            result += (current.getQuantity() * current.getValue());
        }

        return result;
    }

    @Override
    public Integer getAllValueForFulfillmentCenter(String fulfillmentCenter) {
        Integer result = 0;

        List<ProductEntry> productsOfThisCenter =
                productRepository.findAllByFulfillmentCenter(fulfillmentCenter);

        for (int i = 0; i < productsOfThisCenter.size(); i++){
            ProductEntry current = productsOfThisCenter.get(i);
            result += (current.getQuantity() * current.getValue());
        }

        return result;
    }

    private void throwNotFoundProductEntry(String whatDidntFound){
        throw new NotFoundProductEntryException("Not found Product entry with " + whatDidntFound);
    }
}
