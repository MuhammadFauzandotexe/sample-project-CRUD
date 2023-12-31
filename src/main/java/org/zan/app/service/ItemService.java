package org.zan.app.service;

import org.zan.app.dto.ItemUpdateDTO;
import org.zan.app.model.Item;
import org.zan.app.dto.ItemRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    /**
     * Saves an item in the database.
     *
     * @param item The item to be saved.
     * @return The saved item.
     */
    Item save(Item item);

    /**
     * Creates a new item based on the data from the ItemRequestDTO.
     *
     * @param itemRequestDTO Information for the new item.
     * @return The newly created item.
     */
    Item create(ItemRequestDTO itemRequestDTO);

    /**
     * Retrieves all items from the database.
     *
     * @return A list of all items.
     */
    List<Item> getAll();

    /**
     * Retrieves an item by its ID.
     *
     * @param id The ID of the item to be retrieved.
     * @return The item found (if any).
     */
    Item findById(UUID id);

    /**
     * Updates an item based on the information from the ItemUpdateDTO.
     *
     * @param itemUpdateDTO Information for the item to be updated.
     * @return The updated item.
     */
    Item update(ItemUpdateDTO itemUpdateDTO);

    /**
     * Deletes an item by its ID.
     *
     * @param id The ID of the item to be deleted.
     */
    void delete(UUID id);

}
