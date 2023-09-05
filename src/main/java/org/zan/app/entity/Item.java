package org.zan.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Order orders;
}



