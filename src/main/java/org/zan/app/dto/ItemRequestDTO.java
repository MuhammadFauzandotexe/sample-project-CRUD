package org.zan.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
     * The price of the item. It should be a non-negative
     * integer representing the item's price in a certain currency.
     */
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be a non-negative integer")
    private Integer price;
}
