package org.zan.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zan.app.exception.SampleCrudException;
import org.zan.app.model.Item;
import org.zan.app.model.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;
import org.zan.app.service.OrderService;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    private static final Marker TRANSACTION = MarkerManager.getMarker("TRANSACTION");
    /**
     * {@inheritDoc}
     * @throws  if the item with the given ID is not found.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order create(OrderRequestDTO orderRequestDTO) {
        log.info(TRANSACTION+" start create order with item ID: "+orderRequestDTO.getItemId());
        Optional<Item> item  = itemService.findById(orderRequestDTO.getItemId());
        if (item.isEmpty()){
            log.error("item not found with ID: "+orderRequestDTO.getItemId());
            throw new SampleCrudException("Item not found with ID: "+orderRequestDTO.getItemId(),HttpStatus.NOT_FOUND);
        }
        Order order = new Order();
        order.setOrderNo(orderRequestDTO.getOrderNo());
        order.setItems(List.of(item.get()));
        order.setQuantity(orderRequestDTO.getQuantity());
        Order orderCreated = orderRepository.saveAndFlush(order);
        log.info(TRANSACTION+ " success create order with id"+ orderCreated.getId());
        return orderCreated;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        log.info(TRANSACTION+ " get all orders");
        return orderRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the order with the given ID is not found.
     */
    @Override
    public Optional<Order> findById(UUID id) {
        log.info(TRANSACTION+ "get order with ID: "+id);
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new SampleCrudException("order not found with ID: "+id,HttpStatus.NOT_FOUND);
        }
        log.info(TRANSACTION+ " success get order with ID: "+id);
        return orderOptional;
    }

    /**
     *{@inheritDoc}
     * @throws SampleCrudException if the order with the given ID is not found or
     * if the item with the given ID is not found .
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Order update(OrderUpdateDTO orderUpdateDTO) {
        log.info(TRANSACTION+ " start update order with id: " + orderUpdateDTO.getId());
        Optional<Order> orderOptional = findById(orderUpdateDTO.getId());
        if (orderOptional.isEmpty()) {
            throw new SampleCrudException("order not found with ID: "+orderUpdateDTO.getId());
        }
        Optional<Item> itemOptional = itemService.findById(orderUpdateDTO.getItemId());
        if (itemOptional.isEmpty()){
            throw new SampleCrudException(" order not found with ID: "+orderUpdateDTO.getItemId(),HttpStatus.NOT_FOUND);
        }
        orderOptional.get().setItems(List.of(itemOptional.get()));
        orderOptional.get().setOrderNo(orderUpdateDTO.getOrderNo());
        orderOptional.get().setQuantity(orderUpdateDTO.getQuantity());
        log.info(TRANSACTION+ "success update Order with ID :"+orderUpdateDTO.getId());
        return orderOptional.get();
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the order with the given ID is not found.
     */
    @Override
    public void delete(UUID id) {
        log.info(TRANSACTION+ " start delete order with ID: "+id);
        Optional<Order> order = findById(id);
        if(order.isEmpty()){
            throw new SampleCrudException("order not found with ID: "+id, HttpStatus.NOT_FOUND);
        }
        orderRepository.delete(order.get());
        log.info(TRANSACTION+ " success delete order with ID: "+id);
    }
}
