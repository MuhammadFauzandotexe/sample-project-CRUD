package org.zan.app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
@Data
@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Integer id;
    private String orderNo;
    @OneToOne
    private Item item;
    private Integer Qty;
}
