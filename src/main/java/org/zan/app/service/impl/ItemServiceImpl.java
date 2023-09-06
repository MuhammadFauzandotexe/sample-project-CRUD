package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.exception.SampleAppException;
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
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    /**
     * {@inheritDoc}
     */
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        return itemRepository.saveAndFlush(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getAll() {
        log.info("get all item");
        return itemRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @throws SampleAppException if the item with the given ID is not found.
     */
    @Override
    public Optional<Item> findById(Integer id) {
        log.info("get item with id :"+ id);
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            log.error("item not found with ID: "+id);
            throw new SampleAppException("item not found with ID: "+id);
        }
        return item;
    }

    /**
     * {@inheritDoc}
     * @throws SampleAppException if the item with the given ID is not found.
     */
    @Override
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        log.info("start update item with ID: "+ itemUpdateDTO.getId());
        Optional<Item> itemOptional = findById(itemUpdateDTO.getId());
        if (itemOptional.isEmpty()){
            throw new SampleAppException("item not found with ID: "+itemUpdateDTO.getId());
        }
        itemOptional.get().setName(itemUpdateDTO.getName());
        itemOptional.get().setPrice(itemUpdateDTO.getPrice());
        Item itemUpdate = itemRepository.save(itemOptional.get());
        log.info("success update item with ID: "+itemUpdateDTO.getId());
        return itemUpdate;
    }
    
    /**
     * {@inheritDoc}
     * @throws SampleAppException if the item with the given ID is not found.
     */
    @Override
    public void delete(Integer id) {
        log.info("start delete item with ID: "+id);
        Optional<Item> item = findById(id);
        if(item.isEmpty()){
            throw new SampleAppException("item not found with ID: "+id);
        }
        itemRepository.deleteById(id);
        log.info("success delete item with ID: "+id);
    }
}
