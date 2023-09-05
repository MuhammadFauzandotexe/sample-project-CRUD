package org.zan.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderUpdateDTO {
    private Integer id;
    @JsonProperty("item_id")
    private Integer itemId;
    private Integer quantity;;
    @JsonProperty("order_no")
    private String orderNo;
}
