package org.zan.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.model.Order;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderNo("ORDER123");
        orderRequestDTO.setItemId(1);
        orderRequestDTO.setQuantity(2);

        Order newOrder = new Order();
        newOrder.setId(1);
        newOrder.setOrderNo("ORDER123");
        newOrder.setQuantity(2);

        when(orderService.create(any())).thenReturn(newOrder);

        ResponseEntity<CommonResponseDTO<Order>> responseEntity = orderController.create(orderRequestDTO);

        verify(orderService, times(1)).create(any());
        assert(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assert(responseEntity.getBody().getMessage().equals("Success"));
        assert(responseEntity.getBody().getData().getId() == 1);
    }

    @Test
    void testGetAllOrders() throws Exception {
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

        when(orderService.getAll()).thenReturn(orderList);

        ResponseEntity<CommonResponseDTO<?>> responseEntity = orderController.getAll();

        verify(orderService, times(1)).getAll();
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(responseEntity.getBody().getMessage().equals("Success"));
        assert(((List<Order>) responseEntity.getBody().getData()).size() == 2);
    }

    @Test
    void testGetOrderById() throws Exception {
        Integer orderId = 1;
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNo("ORDER123");
        order.setQuantity(2);

        when(orderService.findById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<CommonResponseDTO<Optional<Order>>> responseEntity = orderController.getByid(orderId);

        verify(orderService, times(1)).findById(orderId);
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(responseEntity.getBody().getMessage().equals("Success"));
        assert(responseEntity.getBody().getData().isPresent());
        assert(responseEntity.getBody().getData().get().getId() == 1);
    }

    @Test
    void testUpdateOrder() throws Exception {
        OrderUpdateDTO orderUpdateDTO = new OrderUpdateDTO();
        orderUpdateDTO.setId(1);
        orderUpdateDTO.setOrderNo("UPDATED_ORDER");
        orderUpdateDTO.setItemId(2);
        orderUpdateDTO.setQuantity(4);

        Order updatedOrder = new Order();
        updatedOrder.setId(1);
        updatedOrder.setOrderNo("UPDATED_ORDER");
        updatedOrder.setQuantity(4);

        when(orderService.update(any())).thenReturn(updatedOrder);

        ResponseEntity<CommonResponseDTO<Order>> responseEntity = orderController.update(orderUpdateDTO);

        verify(orderService, times(1)).update(any());
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(responseEntity.getBody().getMessage().equals("success update"));
        assert(responseEntity.getBody().getData().getId() == 1);
    }

    @Test
    void testDeleteOrder() throws Exception {
        Integer orderId = 1;

        ResponseEntity<Void> responseEntity = orderController.delete(orderId);

        verify(orderService, times(1)).delete(orderId);
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
    }
}
