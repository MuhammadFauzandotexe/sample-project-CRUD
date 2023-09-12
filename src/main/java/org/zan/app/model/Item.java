package org.zan.app.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The price of the item.
     */
    private Integer price;

}




