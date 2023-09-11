package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.model.Order;

import java.util.UUID;

/**
 * Repository for order
 * @author :Muhammad Fauzan
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
