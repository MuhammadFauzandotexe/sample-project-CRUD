package org.zan.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Represents an order entity in the application.
 * This class defines the properties and relationships of an order.
 *
 * @author :Muhammad Fauzan
 */
@Entity
@Table(name = "t_order")
@Getter
@Setter
public class Order {

    /**
     * The unique identifier of the order.
     */
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The order number.
     */
    private String orderNo;

    /**
     * The list of items included in the order.
     * This is a many-to-many relationship, and it is mapped through the "order_item" join table.
     */
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

}



