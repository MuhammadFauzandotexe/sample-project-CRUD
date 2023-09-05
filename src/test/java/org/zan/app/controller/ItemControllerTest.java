package org.zan.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.CommonResponseDTO;
import org.zan.app.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateItem() throws Exception {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setName("Test Item");
        itemRequestDTO.setPrice(100);

        Item newItem = new Item();
        newItem.setId(1);
        newItem.setName("Test Item");
        newItem.setPrice(100);

        when(itemService.create(any())).thenReturn(newItem);

        ResponseEntity<CommonResponseDTO<Item>> responseEntity = itemController.create(itemRequestDTO);

        verify(itemService, times(1)).create(any());
        assert(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assert(responseEntity.getBody().getMessage().equals("Item created"));
        assert(responseEntity.getBody().getData().getId() == 1);
    }

    @Test
    void testGetItemById() throws Exception {
        Integer itemId = 1;
        Item item = new Item();
        item.setId(itemId);
        item.setName("Test Item");
        item.setPrice(100);

        when(itemService.findById(itemId)).thenReturn(Optional.of(item));

        ResponseEntity<CommonResponseDTO<Item>> responseEntity = itemController.getById(itemId);

        verify(itemService, times(1)).findById(itemId);
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(responseEntity.getBody().getMessage().equals("Success"));
        assert(responseEntity.getBody().getData().getId() == 1);
    }

    @Test
    void testGetAllItems() throws Exception {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Item 1");
        item1.setPrice(100);

        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Item 2");
        item2.setPrice(200);

        itemList.add(item1);
        itemList.add(item2);

        when(itemService.getAll()).thenReturn(itemList);

        ResponseEntity<CommonResponseDTO<List<Item>>> responseEntity = itemController.getAll();

        verify(itemService, times(1)).getAll();
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
        assert(responseEntity.getBody().getMessage() == "Success");
        assert(responseEntity.getBody().getData().size() == 2);
    }

    @Test
    void testUpdateItem() throws Exception {
        ItemUpdateDTO itemUpdateDTO = new ItemUpdateDTO();
        itemUpdateDTO.setId(1);
        itemUpdateDTO.setName("Updated Item");
        itemUpdateDTO.setPrice(150);

        Item updatedItem = new Item();
        updatedItem.setId(1);
        updatedItem.setName("Updated Item");
        updatedItem.setPrice(150);

        when(itemService.update(any())).thenReturn(updatedItem);

        ResponseEntity<CommonResponseDTO<Item>> responseEntity = itemController.update(itemUpdateDTO);

        verify(itemService, times(1)).update(any());
        assert(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assert(responseEntity.getBody().getMessage() == "Success update");
        assert(responseEntity.getBody().getData().getId() == 1);
    }

    @Test
    void testDeleteItem() throws Exception {
        Integer itemId = 1;
        when(itemService.findById(itemId)).thenReturn(Optional.of(new Item()));

        ResponseEntity<Void> responseEntity = itemController.delete(itemId);

        verify(itemService, times(1)).findById(itemId);
        verify(itemService, times(1)).delete(itemId);
        assert(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void testDeleteItem_NotFound() throws Exception {
        Integer itemId = 1;
        when(itemService.findById(itemId)).thenReturn(Optional.empty());

        ResponseEntity<Void> responseEntity = itemController.delete(itemId);

        verify(itemService, times(1)).findById(itemId);
        verify(itemService, never()).delete(itemId);
        assert(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
    }
}
