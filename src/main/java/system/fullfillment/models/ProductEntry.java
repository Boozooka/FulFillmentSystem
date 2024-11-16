package system.fullfillment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntry {

    @Id
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "status")
    private String status;

    @Column(name = "fulfillmentCenter")
    private String fulfillmentCenter;

    @Column(name = "qty")
    private Integer quantity;

    @Column(name = "val")
    private Integer value;

}
