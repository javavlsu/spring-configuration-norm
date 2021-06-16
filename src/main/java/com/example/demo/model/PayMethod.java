package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "paymethods")
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id",unique = true)
    private int id;
    @Column(name = "pay_method", length = 50)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description", length = 100)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "payMethod")
    private List<Order> order;

    public PayMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

