package org.zan.app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "m_item")
@SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Integer id;
    private String name;
    private Integer price;
}
