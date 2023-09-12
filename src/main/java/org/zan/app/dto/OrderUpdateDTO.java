package org.zan.app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing an update request for an order.
 * It includes information such as the order's ID, item ID, quantity, and order number.
 */
@Getter
@Setter
public class OrderUpdateDTO {
    private UUID id;
    private String orderNo;
}
