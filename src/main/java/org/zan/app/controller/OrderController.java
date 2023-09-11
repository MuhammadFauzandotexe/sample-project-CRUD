package org.zan.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zan.app.model.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.service.OrderService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller class for managing order-related operations.
 * Exposes RESTful endpoints for creating, retrieving, updating, and deleting orders.
 * @author :Muhammad Fauzan
 */
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * Endpoint for creating a new order.
     *
     * @param orderRequestDTO The request body containing order details to be created.
     * @return ResponseEntity containing a CommonResponseDTO with the created order and a success message.
     */
    @PostMapping("/create")
    public ResponseEntity<CommonResponseDTO<Order>> create(@RequestBody OrderRequestDTO orderRequestDTO){
        Order order = orderService.create(orderRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CommonResponseDTO.<Order>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Success")
                                .data(order)
                                .build()
                );
    }

    /**
     * Endpoint for retrieving a list of all orders.
     *
     * @return ResponseEntity containing a CommonResponseDTO with a list of orders and a success message.
     */
    @GetMapping("/all")
    public ResponseEntity<CommonResponseDTO<?>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(
                        CommonResponseDTO.<List<Order>>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(orderService.getAll())
                                .build()
                );
    }

    /**
     * Endpoint for retrieving an order by its ID.
     *
     * @param id The unique identifier of the order.
     * @return ResponseEntity containing a CommonResponseDTO with the retrieved order and a success message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDTO<Optional<Order>>> getById(@PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponseDTO.<Optional<Order>>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(orderService.findById(id))
                                .build()
                );
    }

    /**
     * Endpoint for updating an existing order.
     *
     * @param orderUpdateDTO The request body containing order details to be updated.
     * @return ResponseEntity containing a CommonResponseDTO with the updated order and a success message.
     */
    @PutMapping
    public ResponseEntity<CommonResponseDTO<Order>> update(@RequestBody OrderUpdateDTO orderUpdateDTO){
        Order update = orderService.update(orderUpdateDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponseDTO.<Order>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("success update")
                                .data(update)
                                .build()
                );
    }

    /**
     * Endpoint for deleting an order by its ID.
     *
     * @param id The unique identifier of the order to be deleted.
     * @return ResponseEntity indicating the success or failure of the deletion operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
