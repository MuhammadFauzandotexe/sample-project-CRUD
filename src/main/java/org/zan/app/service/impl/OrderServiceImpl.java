package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.entity.Item;
import org.zan.app.entity.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.repository.ItemRepository;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;
import org.zan.app.service.OrderService;
import javax.transaction.Transactional;
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
    public Order create(OrderRequestDTO orderRequestDTO) {
        Optional<Item> item  = itemRepository.findById(orderRequestDTO.getItemId());
        Order order = new Order();
        order.setOrderNo(orderRequestDTO.getOrderNo());
        order.setItems(List.of(item.get()));
        Order order1 = orderRepository.saveAndFlush(order);
        return order1;
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
    public Order update(OrderUpdateDTO orderUpdateDTO) {
        log.info("start update order with id: "+ orderUpdateDTO.getId());
        Optional<Order> order = findById(orderUpdateDTO.getId());
        if (order.isPresent()){
            Optional<Item> item = itemService.findById(orderUpdateDTO.getItemId());
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
