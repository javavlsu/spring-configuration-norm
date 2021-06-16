package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private  int id;
    @Column(length = 50,name = "Category", nullable = false, unique = true, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "Description", length = 100, nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @OneToMany( mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;
    public List<Product> getProducts() {
        return products;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
