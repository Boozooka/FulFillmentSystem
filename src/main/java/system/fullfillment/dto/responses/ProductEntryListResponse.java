package system.fullfillment.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.fullfillment.models.ProductEntry;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntryListResponse {
    String body;

    public ProductEntryListResponse(List<ProductEntry> entries){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.size(); i++){
            ProductEntry current = entries.get(i);
            String increase = "(Id: " + current.getId() + ", Product id: " + current.getProductId()
                    + ", Status:" + current.getStatus() + ", Fulfillment center:" + current.getFulfillmentCenter() +
                    ", Quantity :" + current.getQuantity() + ", Value:" + current.getValue() + ")";
            sb.append(increase);
        }
        body = sb.toString();
    }
}
