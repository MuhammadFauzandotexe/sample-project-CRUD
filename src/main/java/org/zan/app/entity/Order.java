package org.zan.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_order")
@Data
@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Integer id;
    private String orderNo;

    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();
}



