package org.zan.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;


    /**
     * The order number.
     */
    private String orderNo;

    /**
     * The quantity of items in the order.
     * It cannot be null and must be a non-negative integer.
     */
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be a non-negative integer")
    private Integer quantity;

    /**
     * The list of items included in the order.
     * This is a many-to-many relationship, and it is mapped through the "order_item" join table.
     */
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();
}



