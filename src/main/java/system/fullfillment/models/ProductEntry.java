package system.fullfillment.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
