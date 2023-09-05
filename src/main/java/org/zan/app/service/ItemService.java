package org.zan.app.service;

import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.entity.Item;
import org.zan.app.dto.ItemRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item create(ItemRequestDTO itemRequestDTO);
    List<Item> getAll();
    Optional<Item> findById(Integer id);
    Item update(ItemUpdateDTO itemUpdateDTO);
    void delete(Integer id);

}
