package org.zan.app.service;
import org.zan.app.entity.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(OrderRequestDTO orderRequestDTO);
    List<Order> getAll();
    Optional<Order> findById(Integer id);
    Order update(OrderUpdateDTO orderUpdateDTO);
    void delete(Integer id);

}
