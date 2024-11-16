package system.fullfillment.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductEntryRequest {
    private Long id;
    private String updatingColumn;
    private String newValue;
}
