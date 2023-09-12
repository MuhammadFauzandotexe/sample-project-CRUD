package org.zan.app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing an order request.
 * It includes information such as the item ID, quantity, and order number.
 */
@Getter
@Setter
public class OrderRequestDTO {
    private String orderNo;
    private List<OrderItemRequest> orderItems;
}

