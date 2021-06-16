package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Table(name = "orderstates")
@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "orderstate", nullable = false, insertable = true, unique = true, updatable = true, length = 50)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description", nullable = false, insertable = true, updatable = true, length = 100)
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @OneToMany(mappedBy = "orderState", fetch = FetchType.EAGER)
    private List<Order> order;

    public OrderState(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
