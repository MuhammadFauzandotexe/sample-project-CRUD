package org.zan.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderItemRequest {
    private UUID itemId;
    private Integer quantity;
}
