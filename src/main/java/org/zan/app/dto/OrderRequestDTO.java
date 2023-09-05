package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @JsonProperty("item_id")
    private Integer itemId;
    private Integer quantity;;
    @JsonProperty("order_no")
    private String orderNo;
}
