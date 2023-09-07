package org.zan.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an item entity in the application.
 * This class defines the properties and relationships of an item.
 *
 * @author :Muhammad Fauzan
 */
@Entity
@Table(name = "m_items")
@SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence")
@Data
public class Item {

    /**
     * The unique identifier of the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Integer id;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The price of the item.
     */
    private Integer price;

    /**
     * The list of orders that include this item.
     * This is a many-to-many relationship, and it is mapped by the "items" property in the "Order" entity.
     */
    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();
}




