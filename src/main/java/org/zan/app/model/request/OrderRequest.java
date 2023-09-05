package org.zan.app.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderRequest {
    @JsonProperty("item_id")
    private Integer itemId;
    private Integer quantity;;
    @JsonProperty("order_no")
    private String orderNo;
}
