package org.zan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zan.app.model.OrderDetail;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
