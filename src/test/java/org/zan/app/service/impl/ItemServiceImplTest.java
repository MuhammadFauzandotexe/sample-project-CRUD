package org.zan.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.model.Item;
import org.zan.app.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateItem() {
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
        itemRequestDTO.setName("Test Item");
        itemRequestDTO.setPrice(100);

        Item newItem = new Item();
        newItem.setId(1);
        newItem.setName("Test Item");
        newItem.setPrice(100);

        when(itemRepository.saveAndFlush(any())).thenReturn(newItem);

        Item createdItem = itemService.create(itemRequestDTO);

        verify(itemRepository, times(1)).saveAndFlush(any());
        assert(createdItem.getName().equals("Test Item"));
        assert(createdItem.getPrice() == 100);
    }

    @Test
    void testGetAllItems() {
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

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> allItems = itemService.getAll();

        verify(itemRepository, times(1)).findAll();
        assert(allItems.size() == 2);
    }

    @Test
    void testFindItemById() {
        Integer itemId = 1;
        Item item = new Item();
        item.setId(itemId);
        item.setName("Test Item");
        item.setPrice(100);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        Optional<Item> foundItem = itemService.findById(itemId);

        verify(itemRepository, times(1)).findById(itemId);
        assert(foundItem.isPresent());
        assert(foundItem.get().getId().equals(itemId));
    }

}
