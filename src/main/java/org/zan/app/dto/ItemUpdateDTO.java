package org.zan.app.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing an update request for an item.
 * It includes information such as the item's ID, name, and price.
 */
@Getter
@Setter
public class ItemUpdateDTO {

    /**
     * The unique identifier of the item to be updated.
     */
    private UUID id;

    /**
     * The updated name of the item. It should be a descriptive name for the item.
     */
    private String name;

    /**
     * the item's price in a certain currency.
     */
    private Integer price;
}
