package system.fullfillment.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProductEntryRequest {
    private String productId;
    private String status;
    private String fc;
    private Integer qty;
    private Integer value;
}