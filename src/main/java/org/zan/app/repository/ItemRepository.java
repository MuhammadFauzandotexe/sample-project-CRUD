package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.entity.Item;

/**
 * Repository for Item
 * @author nathan
 */
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
