package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.model.Item;

/**
 * Repository for Item
 * @author :Muhammad Fauzan
 */
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
