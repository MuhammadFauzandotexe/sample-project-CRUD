package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.entity.Item;
import org.zan.app.entity.Order;
import org.zan.app.model.request.ItemRequest;
import org.zan.app.repository.ItemRepository;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;
import org.zan.app.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    @Override
    public Item create(ItemRequest itemRequest) {
        log.info("Creating item");
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        Item createdItem = itemRepository.save(item);
        log.info("Item created with id: " + createdItem.getId());
        return createdItem;
    }
    @Override
    public List<Item> getAll() {
        log.info("get all item");
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Integer id) {
        log.info("get item with id :"+ id);
        return itemRepository.findById(id);
    }

    @Override
    public Item update(Item item) {
        log.info("update item with id: "+item.getId());
        Optional<Item> itemOptional = Optional.ofNullable(findById(item.getId())
                .orElseThrow(() -> new RuntimeException("Id Not Found")
                ));
        itemOptional.get().setName(item.getName());
        itemOptional.get().setPrice(item.getPrice());
        Item itemUpdate = itemRepository.save(itemOptional.get());
        log.info("success update item");
        return itemUpdate;
    }

    @Override
    public void delete(Integer id) {
        log.info("start delete item");
        Optional<Item> item = findById(id);
        if (item.isPresent()){
            itemRepository.deleteById(id);
            log.info("success delete item");
        }else log.error("Id Not Found");
    }
}
