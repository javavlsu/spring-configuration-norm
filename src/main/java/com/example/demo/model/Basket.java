package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "basket")
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @Basic(fetch = FetchType.LAZY)
    private int id;
    @Column(nullable = false, name = "Buyer")
    @Basic(fetch = FetchType.LAZY)
    private int buyerId;
    @Column(name = "Product", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private int productId;
    @Column(name = "count", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private int count;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "intermediate_cost", nullable = false)
    private double intermediateCost;
    @JoinColumn(name = "product_fk", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Product product;

    @JoinColumn(nullable = false, name = "id_t", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private  User buyer;
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "basket")
    @OneToMany(mappedBy = "basket")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> order;




}
