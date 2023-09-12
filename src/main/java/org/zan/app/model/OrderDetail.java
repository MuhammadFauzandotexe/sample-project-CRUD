package org.zan.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "t_order_detail")
@Getter
@Setter
public class OrderDetail {
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer quantity;

    @ManyToMany
    @JoinTable(
            name = "order_detail_item",
            joinColumns = @JoinColumn(name = "order_detail_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    @ManyToOne
    @JsonBackReference
    private Order order;
}
