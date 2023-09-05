package org.zan.app.service;

import org.zan.app.entity.Item;
import org.zan.app.model.request.ItemRequest;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item create(ItemRequest itemRequest);
    List<Item> getAll();
    Optional<Item> findById(Integer id);
    Item update(Item item);
    void delete(Integer id);

}
