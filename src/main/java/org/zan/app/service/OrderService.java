package org.zan.app.service;
import org.zan.app.entity.Order;
import org.zan.app.model.request.OrderRequest;
import org.zan.app.model.request.OrderUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(OrderRequest orderRequest);
    List<Order> getAll();
    Optional<Order> findById(Integer id);
    Order update(OrderUpdateRequest orderUpdateRequest);
    void delete(Integer id);

}
