package org.zan.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a data transfer object (DTO) for creating or updating an item.
 * It includes information such as the item's name and price.
 */
@Getter
@Setter
public class ItemRequestDTO {
    /**
     * The name of the item. It should be a descriptive name for the item.
     */
    private String name;
    /**
     * integer representing the item's price in a certain currency.
     */
    private Integer price;
}
