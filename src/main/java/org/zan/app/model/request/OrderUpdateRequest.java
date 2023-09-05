package org.zan.app.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderUpdateRequest {
    private Integer id;
    @JsonProperty("item_id")
    private Integer itemId;
    private Integer quantity;;
    @JsonProperty("order_no")
    private String orderNo;
}
