package org.zan.app.service;
import org.zan.app.entity.Order;
import org.zan.app.dto.OrderRequestDTO;
import org.zan.app.dto.OrderUpdateDTO;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    /**
     * Creates a new order based on the data from the OrderRequestDTO.
     *
     * @param orderRequestDTO Information for the new order.
     * @return The newly created order.
     */
    Order create(OrderRequestDTO orderRequestDTO);

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of all orders.
     */
    List<Order> getAll();

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to be retrieved.
     * @return The order found (if any).
     */
    Optional<Order> findById(Integer id);

    /**
     * Updates an order based on the information from the OrderUpdateDTO.
     *
     * @param orderUpdateDTO Information for the order to be updated.
     * @return The updated order.
     */
    Order update(OrderUpdateDTO orderUpdateDTO);
    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     */
    void delete(Integer id);

}
