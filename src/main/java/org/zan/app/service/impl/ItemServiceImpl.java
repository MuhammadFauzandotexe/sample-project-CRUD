package org.zan.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
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
    private final Tracer tracer;
    /**
     * {@inheritDoc}
     */
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        Span span = tracer.spanBuilder().start();
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        Item itemCreated = itemRepository.saveAndFlush(item);
        span.end();
        return itemCreated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getAll() {
        Span span = tracer.spanBuilder().start();
        log.info("get all item");
        List<Item> items = itemRepository.findAll();
        span.end();
        return items;
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Optional<Item> findById(String id) {
        Span span = tracer.spanBuilder().start();
        log.info("get item with id :"+ id);
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new SampleCrudException("item not found with ID: "+id,HttpStatus.NOT_FOUND);
        }
        span.end();
        return item;
    }

    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        Span span = tracer.spanBuilder().start();
        log.info("start update item with ID: "+ itemUpdateDTO.getId());
        Optional<Item> itemOptional = findById(itemUpdateDTO.getId());
        if (itemOptional.isEmpty()){
            throw new SampleCrudException("item not found with ID: "+itemUpdateDTO.getId(),HttpStatus.NOT_FOUND);
        }
        itemOptional.get().setName(itemUpdateDTO.getName());
        itemOptional.get().setPrice(itemUpdateDTO.getPrice());
        Item itemUpdate = itemRepository.save(itemOptional.get());
        log.info("success update item with ID: "+itemUpdateDTO.getId());
        span.end();
        return itemUpdate;
    }
    
    /**
     * {@inheritDoc}
     * @throws SampleCrudException if the item with the given ID is not found.
     */
    @Override
    public void delete(String id) {
        Span span = tracer.spanBuilder().start();
        log.info("start delete item with ID: "+id);
        Optional<Item> item = findById(id);
        if(item.isEmpty()){
            throw new SampleCrudException("item not found with ID: "+id, HttpStatus.NOT_FOUND);
        }
        itemRepository.deleteById(id);
        log.info("success delete item with ID: "+id);
        span.end();
    }
}
