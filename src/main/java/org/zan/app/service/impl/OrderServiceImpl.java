package org.zan.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.dto.OrderItemRequest;
import org.zan.app.exception.SampleCrudException;
import org.zan.app.model.Item;
import org.zan.app.model.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.model.OrderDetail;
import org.zan.app.repository.OrderDetailRepository;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;
import org.zan.app.service.OrderService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * This is the implementation class for the order service.
 * This class provides the implementation of the {@link OrderService} interface
 * and is used to manage operations related to order such as creating, fetching,
 * updating, and deleting order.
 *
 * @author Muhammad Fauzan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final OrderDetailRepository orderDetailRepository;
    /**
     * {@inheritDoc}
     * @throws  if the item with the given ID is not found.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order create(OrderRequestDTO orderRequestDTO) {
        log.info("start create order with OrderNo: ");
        Order order = new Order();
        order.setOrderNo(orderRequestDTO.getOrderNo());
        Order orderSave = orderRepository.saveAndFlush(order);
        log.info(orderRequestDTO.toString());
        log.info(orderRequestDTO.getOrderItems().toString());
        for (OrderItemRequest itemRequest :orderRequestDTO.getOrderItems()) {
            Item item = itemService.findById(itemRequest.getItemId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(orderSave);
            orderDetail.setQuantity(itemRequest.getQuantity());
            orderDetail.setItems(List.of(item));
            orderDetailRepository.saveAndFlush(orderDetail);
            itemService.save(item);
        }
        log.info("success create order with ID: "+orderSave.getId());
        return orderSave;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        log.info("get all orders");
        return orderRepository.findAll();
    }

    /**
     * {@inheritDoc}
     *
     * @throws SampleCrudException if the order with the given ID is not found.
     */
    @Override
    public Order findById(UUID id) {
        log.info("get order with ID: "+id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new SampleCrudException("Order with ID " + id + " not found"));
    }

    /**
     *{@inheritDoc}
     * @throws SampleCrudException if the order with the given ID is not found or
     * if the item with the given ID is not found .
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order update(OrderUpdateDTO orderUpdateDTO) {
        log.info("start update order with id: "+orderUpdateDTO.getId());
        Order order = orderRepository.getReferenceById(orderUpdateDTO.getId());
        order.setOrderNo(orderUpdateDTO.getOrderNo());
        orderRepository.save(order);
        log.info("success update order with ID: "+orderUpdateDTO.getId());
        return order;
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the order with the given ID is not found.
     */
    @Override
    public void delete(UUID id) {
        log.info("start delete order with ID: "+id);
        Order order = findById(id);
        orderRepository.delete(order);
        log.info("success delete order with ID: "+id);
    }
}
