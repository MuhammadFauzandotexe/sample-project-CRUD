package org.zan.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.exception.SampleCrudException;
import org.zan.app.model.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.repository.ItemRepository;
import org.zan.app.service.ItemService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This is the implementation class for the item service.
 * This class provides the implementation of the {@link ItemService} interface
 * and is used to manage operations related to items such as creating, fetching,
 * updating, and deleting items.
 *
 * @author Muhammad Fauzan
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Item save(Item item) {
        log.info("start save item");
        Item save = itemRepository.saveAndFlush(item);
        log.info("success save item");
        return save;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        log.info(" start create item ");
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        Item itemCreated = itemRepository.saveAndFlush(item);
        log.info(" success create item with id: "+itemCreated.getId());
        return itemCreated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getAll() {
        log.info(" get all item");
        List<Item> items = itemRepository.findAll();
        log.info(" success get all items");
        return items;
    }

    /**
     * {@inheritDoc}
     *
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Item findById(UUID id) {
        log.info(" get item with id :"+ id);
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new SampleCrudException(" item not found with ID: "+id,HttpStatus.NOT_FOUND);
        }
        return item.get();
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        log.info(" start update item with ID: "+ itemUpdateDTO.getId());
        Item item = findById(itemUpdateDTO.getId());
        item.setName(itemUpdateDTO.getName());
        item.setPrice(itemUpdateDTO.getPrice());
        Item itemUpdate = itemRepository.save(item);
        log.info(" success update item with ID: "+itemUpdateDTO.getId());
        return itemUpdate;
    }
    
    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public void delete(UUID id) {
        log.info(" start delete item with ID: "+id);
        Item item = findById(id);
        itemRepository.delete(item);
        log.info(" success delete item with ID: "+id);
    }
}
