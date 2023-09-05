package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
