package org.zan.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an item entity in the application.
 * This class defines the properties and relationships of an item.
 *
 * @author :Muhammad Fauzan
 */
@Entity
@Table(name = "m_items")
@Getter
@Setter
public class Item {

    /**
     * The unique identifier of the item.
     */
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


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




