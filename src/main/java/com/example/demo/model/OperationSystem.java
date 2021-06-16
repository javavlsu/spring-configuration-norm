package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "operationsystems")
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "name", updatable = true, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description",nullable = false, updatable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @OneToMany(mappedBy = "operationSystem", fetch = FetchType.EAGER)
    private List<Product> product;

    public OperationSystem(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

