package org.zan.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.service.ItemService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/item")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @PostMapping
    public ResponseEntity<CommonResponseDTO<Item>> create(@RequestBody ItemRequestDTO itemRequestDTO){
        Item item = itemService.create(itemRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CommonResponseDTO.<Item>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Item created")
                                .data(item)
                                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDTO<Item>> getById(@PathVariable Integer id){
        Optional<Item> item = itemService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponseDTO.<Item>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(item.get())
                                .build()
                );
    }
    @GetMapping("/all")
    public ResponseEntity<CommonResponseDTO<List<Item>>> getAll(){
        List<Item> items = itemService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponseDTO.<List<Item>>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(items)
                                .build()
                );
    }
    @PutMapping()
    public ResponseEntity<CommonResponseDTO<Item>> update(@RequestBody ItemUpdateDTO item){
        Item update = itemService.update(item);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CommonResponseDTO.<Item>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success update")
                                .data(update)
                                .build()
                );
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Optional<Item> item = itemService.findById(id);
        if (item.isPresent()){
            itemService.delete(id);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }

}
