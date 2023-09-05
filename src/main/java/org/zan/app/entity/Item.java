package org.zan.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "m_items")
@SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Integer id;
    private String name;
    private Integer price;

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();
}




