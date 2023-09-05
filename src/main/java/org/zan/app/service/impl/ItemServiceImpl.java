package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.repository.ItemRepository;
import org.zan.app.service.ItemService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    /**
     * Creates a new item based on the data from the ItemRequestDTO.
     *
     * @param itemRequestDTO Information for the new item.
     * @return The newly created item.
     */
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        return itemRepository.saveAndFlush(item);
    }

    /**
     * Retrieves all items from the database.
     *
     * @return A list of all items.
     */
    @Override
    public List<Item> getAll() {
        log.info("get all item");
        return itemRepository.findAll();
    }

    /**
     * Retrieves an item by its ID.
     *
     * @param id The ID of the item to be retrieved.
     * @return The item found (if any).
     */
    @Override
    public Optional<Item> findById(Integer id) {
        log.info("get item with id :"+ id);
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            log.error("item not found with ID: "+id);
            throw new RuntimeException("item not found with ID: "+id);
        }
        return item;
    }

    /**
     * Updates an item based on the information from the ItemUpdateDTO.
     *
     * @param itemUpdateDTO Information for the item to be updated.
     * @return The updated item.
     */
    @Override
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        log.info("start update item with ID: "+ itemUpdateDTO.getId());
        Optional<Item> itemOptional = findById(itemUpdateDTO.getId());
        if (itemOptional.isEmpty()){
            log.error("item not found with ID: "+itemUpdateDTO.getId());
            throw new RuntimeException("item not found with ID: "+itemUpdateDTO.getId());
        }
        itemOptional.get().setName(itemUpdateDTO.getName());
        itemOptional.get().setPrice(itemUpdateDTO.getPrice());
        Item itemUpdate = itemRepository.save(itemOptional.get());
        log.info("success update item with ID: "+itemUpdateDTO.getId());
        return itemUpdate;
    }
    
    /**
     * Deletes an item by its ID.
     *
     * @param id The ID of the item to be deleted.
     */
    @Override
    public void delete(Integer id) {
        log.info("start delete item with ID: "+id);
        Optional<Item> item = findById(id);
        if(item.isEmpty()){
            log.error("item not found with ID: "+id);
            throw new RuntimeException("item not found with ID: "+id);
        }
        itemRepository.deleteById(id);
        log.info("success delete item with ID: "+id);
    }
}
