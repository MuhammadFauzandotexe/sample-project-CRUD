package org.zan.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zan.app.entity.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    @PostMapping
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
    @GetMapping("{id}")
    public ResponseEntity<CommonResponseDTO<Optional<Order>>> getByid(@PathVariable Integer id){
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
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
