package org.zan.app.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.ItemRequestDTO;
import org.zan.app.repository.ItemRepository;
import org.zan.app.repository.OrderRepository;
import org.zan.app.service.ItemService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    @Override
    public Item create(ItemRequestDTO itemRequestDTO) {
        Item item = new Item();
        item.setPrice(itemRequestDTO.getPrice());
        item.setName(itemRequestDTO.getName());
        return itemRepository.saveAndFlush(item);
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
    public Item update(ItemUpdateDTO itemUpdateDTO) {
        log.info("update item with id: "+ itemUpdateDTO.getId());
        Optional<Item> itemOptional = Optional.ofNullable(findById(itemUpdateDTO.getId())
        .orElseThrow(() -> new RuntimeException("Id Not Found")
        ));
        itemOptional.get().setName(itemUpdateDTO.getName());
        itemOptional.get().setPrice(itemUpdateDTO.getPrice());
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
