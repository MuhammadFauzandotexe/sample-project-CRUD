package org.zan.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.model.Item;
import org.zan.app.model.Order;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateOrder() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderNo("ORDER123");
        orderRequestDTO.setItemId(1);
        orderRequestDTO.setQuantity(2);

        Item item = new Item();
        item.setId(1);
        item.setName("Test Item");
        item.setPrice(100);

        when(itemService.findById(1)).thenReturn(Optional.of(item));

        Order createdOrder = new Order();
        createdOrder.setId(1);
        createdOrder.setOrderNo("ORDER123");
        createdOrder.setItems(List.of(item));
        createdOrder.setQuantity(2);

        when(orderRepository.saveAndFlush(any())).thenReturn(createdOrder);

        Order newOrder = orderService.create(orderRequestDTO);

        verify(itemService, times(1)).findById(1);
        verify(orderRepository, times(1)).saveAndFlush(any());
        assert(newOrder.getOrderNo().equals("ORDER123"));
        assert(newOrder.getQuantity() == 2);
        assert(newOrder.getItems().size() == 1);
        assert(newOrder.getItems().get(0).getName().equals("Test Item"));
    }

    @Test
    void testGetAllOrders() {
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderNo("ORDER123");
        order1.setQuantity(2);

        Order order2 = new Order();
        order2.setId(2);
        order2.setOrderNo("ORDER456");
        order2.setQuantity(3);

        orderList.add(order1);
        orderList.add(order2);

        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> allOrders = orderService.getAll();

        verify(orderRepository, times(1)).findAll();
        assert(allOrders.size() == 2);
    }

    @Test
    void testFindOrderById() {
        Integer orderId = 1;
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNo("ORDER123");
        order.setQuantity(2);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.findById(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        assert(foundOrder.isPresent());
        assert(foundOrder.get().getId().equals(orderId));
    }

    @Test
    void testUpdateOrder() {
        OrderUpdateDTO orderUpdateDTO = new OrderUpdateDTO();
        orderUpdateDTO.setId(1);
        orderUpdateDTO.setOrderNo("UPDATED_ORDER");
        orderUpdateDTO.setItemId(2);
        orderUpdateDTO.setQuantity(4);

        Item item = new Item();
        item.setId(2);
        item.setName("Updated Item");
        item.setPrice(150);

        when(orderRepository.findById(1)).thenReturn(Optional.of(new Order()));
        when(itemService.findById(2)).thenReturn(Optional.of(item));

        Order updatedOrder = orderService.update(orderUpdateDTO);

        verify(orderRepository, times(1)).findById(1);
        verify(itemService, times(1)).findById(2);
        assert(updatedOrder.getOrderNo().equals("UPDATED_ORDER"));
        assert(updatedOrder.getQuantity() == 4);
        assert(updatedOrder.getItems().size() == 1);
        assert(updatedOrder.getItems().get(0).getName().equals("Updated Item"));
    }

    @Test
    void testDeleteOrder() {
        Integer orderId = 1;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order()));

        orderService.delete(orderId);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).delete(any());
    }
}
