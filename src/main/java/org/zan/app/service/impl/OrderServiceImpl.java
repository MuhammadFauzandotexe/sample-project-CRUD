package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.entity.Item;
import org.zan.app.entity.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
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

    /**
     * Creates a new order based on the data from the OrderRequestDTO.
     *
     * @param orderRequestDTO Information for the new order.
     * @return The newly created order.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order create(OrderRequestDTO orderRequestDTO) {
        Optional<Item> item  = itemService.findById(orderRequestDTO.getItemId());
        if (item.isEmpty()){
            log.error("item not found with ID: "+orderRequestDTO.getItemId());
            throw new RuntimeException("Item not found with ID: "+orderRequestDTO.getItemId());
        }
        Order order = new Order();
        order.setOrderNo(orderRequestDTO.getOrderNo());
        order.setItems(List.of(item.get()));
        order.setQuantity(orderRequestDTO.getQuantity());
        Order orderCreated = orderRepository.saveAndFlush(order);
        log.info("success create order with id"+ orderCreated.getId());
        return orderCreated;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of all orders.
     */
    @Override
    public List<Order> getAll() {
        log.info("get all orders");
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to be retrieved.
     * @return The order found (if any).
     */
    @Override
    public Optional<Order> findById(Integer id) {
        log.info("get order with ID: "+id);
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            log.error("order not found with ID: "+id);
            throw new RuntimeException("order not found with ID: "+id);
        }
        log.info("success get order with ID: "+id);
        return orderOptional;
    }

    /**
     * Updates an order based on the information from the OrderUpdateDTO.
     *
     * @param orderUpdateDTO Information for the order to be updated.
     * @return The updated order.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order update(OrderUpdateDTO orderUpdateDTO) {
        log.info("start update order with id: " + orderUpdateDTO.getId());
        Optional<Order> orderOptional = findById(orderUpdateDTO.getId());
        if (orderOptional.isEmpty()) {
            log.error("order not found with ID: "+orderUpdateDTO.getId());
            throw new RuntimeException("order not found with ID: "+orderUpdateDTO.getId());
        }
        Optional<Item> itemOptional = itemService.findById(orderUpdateDTO.getItemId());
        if (itemOptional.isEmpty()){
            log.error("order not found with ID: "+orderUpdateDTO.getItemId());
            throw new RuntimeException("order not found with ID: "+orderUpdateDTO.getItemId());
        }
        orderOptional.get().setItems(List.of(itemOptional.get()));
        orderOptional.get().setOrderNo(orderUpdateDTO.getOrderNo());
        orderOptional.get().setQuantity(orderUpdateDTO.getQuantity());
        return orderOptional.get();
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     */
    @Override
    public void delete(Integer id) {
        log.info("start delete order with ID: "+id);
        Optional<Order> order = findById(id);
        if(order.isEmpty()){
            log.error("order not found with ID: "+id);
            throw new RuntimeException("order not found with ID: "+id);
        }
        orderRepository.delete(order.get());
        log.info("success delete order with ID: "+id);
    }
}
