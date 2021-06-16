package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Getter
@Setter
@Transactional
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id",unique = true)
    private int id;
    @Column(name = "cipher",length = 60, nullable = false, updatable = true)
    @Basic(fetch = FetchType.LAZY)
    private String cipher;
    @Column(name = "name", length = 50, updatable = true, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(nullable = false, name = "suppler_company")
    private int supplerCompanyId;
    @Column(nullable = false, name = "operation_system")
    @Basic(fetch = FetchType.LAZY)
    private int operationSystemId;
    @Column(nullable = false, name = "Category")
    @Basic(fetch = FetchType.LAZY)
    private int categoryId;
    @Column(name = "price")
    @Basic(fetch = FetchType.LAZY)
    private double price;
    @Column(name = "count_all")
    @Basic(fetch = FetchType.LAZY)
    private int countAll;
    @Column(name = "ammountsproducts")
    @Basic(fetch = FetchType.LAZY)
    private int amountProducts;
    @Column(name = "rating")
    @Basic(fetch = FetchType.LAZY)
    private double rating;
    @Column(name = "description")
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @JoinColumn(name = "fk_product_category_", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Category category;
    @JoinColumn(name = "operation_system_fk", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private OperationSystem operationSystem;
    @JoinColumn(name = "suppler_company_fk", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private SupplerCompany supplerCompany;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Basket> basket;




}
