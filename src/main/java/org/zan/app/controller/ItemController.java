package org.zan.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.model.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.service.ItemService;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing item-related operations.
 * Exposes RESTful endpoints for creating, retrieving, updating, and deleting items.
 * @author :Muhammad Fauzan
 */
@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /**
     * Endpoint for creating a new item.
     *
     * @param itemRequestDTO The request body containing item details to be created.
     * @return ResponseEntity containing a CommonResponseDTO with the created item and a success message.
     */
    @PostMapping("/create")
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

    /**
     * Endpoint for retrieving an item by its ID.
     *
     * @param id The unique identifier of the item.
     * @return ResponseEntity containing a CommonResponseDTO with the retrieved item and a success message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDTO<Item>> getById(@PathVariable UUID id){
        Item item = itemService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CommonResponseDTO.<Item>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Success")
                                .data(item)
                                .build()
                );
    }

    /**
     * Endpoint for retrieving a list of all items.
     *
     * @return ResponseEntity containing a CommonResponseDTO with a list of items and a success message.
     */
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

    /**
     * Endpoint for updating an existing item.
     * @param item The request body containing item details to be updated.
     * @return ResponseEntity containing a CommonResponseDTO with the updated item and a success message.
     */
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

    /**
     * Endpoint for deleting an item by its ID.
     * @param id The unique identifier of the item to be deleted.
     * @return ResponseEntity indicating the success or failure of the deletion operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
