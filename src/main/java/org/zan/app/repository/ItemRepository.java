package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.model.Item;

import java.util.UUID;

/**
 * Repository for Item
 * @author :Muhammad Fauzan
 */
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
