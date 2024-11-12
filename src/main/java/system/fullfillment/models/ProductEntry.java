package system.fullfillment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product_entries")
@Data
public class ProductEntry {

    @Id
    private Long id;

    private String product;

    private String status;

    private String fulfillmentCenter;

    private Integer quality;

    private Integer value;

}
