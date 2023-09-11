package org.zan.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
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
    private static final Marker TRANSACTION = MarkerManager.getMarker("TRANSACTION");

    /**
     * {@inheritDoc}
     */
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        log.info(TRANSACTION+ " start create item "+TRANSACTION);
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        Item itemCreated = itemRepository.saveAndFlush(item);
        log.info(TRANSACTION+ " success create item with id: "+itemCreated.getId());
        return itemCreated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getAll() {
        log.info(TRANSACTION+ " get all item");
        List<Item> items = itemRepository.findAll();
        log.info(TRANSACTION+ " success get all items");
        return items;
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Optional<Item> findById(UUID id) {
        log.info(TRANSACTION+ " get item with id :"+ id);
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new SampleCrudException(" item not found with ID: "+id,HttpStatus.NOT_FOUND);
        }
        return item;
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        log.info(TRANSACTION+ " start update item with ID: "+ itemUpdateDTO.getId());
        Optional<Item> itemOptional = findById(itemUpdateDTO.getId());
        if (itemOptional.isEmpty()){
            throw new SampleCrudException(" item not found with ID: "+itemUpdateDTO.getId(),HttpStatus.NOT_FOUND);
        }
        itemOptional.get().setName(itemUpdateDTO.getName());
        itemOptional.get().setPrice(itemUpdateDTO.getPrice());
        Item itemUpdate = itemRepository.save(itemOptional.get());
        log.info(TRANSACTION+ " success update item with ID: "+itemUpdateDTO.getId());
        return itemUpdate;
    }
    
    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public void delete(UUID id) {
        log.info(TRANSACTION+ " start delete item with ID: "+id);
        Optional<Item> item = findById(id);
        if(item.isEmpty()){
            throw new SampleCrudException("item not found with ID: "+id, HttpStatus.NOT_FOUND);
        }
        itemRepository.deleteById(id);
        log.info(TRANSACTION+ " success delete item with ID: "+id);
    }
}
