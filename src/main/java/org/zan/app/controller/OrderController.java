package org.zan.app.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zan.app.entity.Item;
import org.zan.app.entity.Order;
import org.zan.app.model.request.OrderRequest;
import org.zan.app.model.request.OrderUpdateRequest;
import org.zan.app.model.response.CommonResponse;
import org.zan.app.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<CommonResponse<Order>> create(@RequestBody OrderRequest orderRequest){
        Order order = orderService.create(orderRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CommonResponse.<Order>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Success")
                                .data(order)
                                .build()
                );
    }
    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<Order>>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(
                        CommonResponse.<List<Order>>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(orderService.getAll())
                                .build()
                );
    }
    @GetMapping("{id}")
    public ResponseEntity<CommonResponse<Optional<Order>>> getByid(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponse.<Optional<Order>>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(orderService.findById(id))
                                .build()
                );
    }
    @PutMapping
    public ResponseEntity<CommonResponse<Order>> update(@RequestBody OrderUpdateRequest orderUpdateRequest){
        Order update = orderService.update(orderUpdateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponse.<Order>builder()
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
