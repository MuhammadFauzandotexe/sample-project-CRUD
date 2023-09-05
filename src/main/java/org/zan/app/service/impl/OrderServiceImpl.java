package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.entity.Item;
import org.zan.app.entity.Order;
import org.zan.app.model.request.OrderRequest;
import org.zan.app.model.request.OrderUpdateRequest;
import org.zan.app.repository.ItemRepository;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;
import org.zan.app.service.OrderService;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order create(OrderRequest orderRequest) {
        log.info("Start creating order");
        Order order = new Order();
        order.setOrderNo(orderRequest.getOrderNo());
        Optional<Item> itemOptional = itemService.findById(orderRequest.getItemId());
        if (itemOptional.isEmpty()) {
            throw new RuntimeException("Item Not Found");
        }
        Order orderCreated = orderRepository.saveAndFlush(order);
        log.info("Success creating order with id: " + orderCreated.getId());
        itemOptional.get().setOrders(orderCreated);
        return orderCreated;
    }


    @Override
    public List<Order> getAll() {
        log.info("get all");
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        log.info("find by id");
        return orderRepository.findById(id);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order update(OrderUpdateRequest orderUpdateRequest) {
        log.info("start update order with id: "+orderUpdateRequest.getId());
        Optional<Order> order = findById(orderUpdateRequest.getId());
        if (order.isPresent()){
            Optional<Item> item = itemService.findById(orderUpdateRequest.getItemId());
            orderRepository.save(order.get());
            log.info("success update with id: "+order.get().getId());
        }
        return order.get();
    }

    @Override
    public void delete(Integer id) {
        log.info("start delete");
        Optional<Order> order = findById(id);
        if(order.isPresent()){
            log.info("success delete with : "+id);
        }else log.error("id "+id+" not found");
    }
}
