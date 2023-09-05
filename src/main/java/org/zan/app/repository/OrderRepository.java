package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
