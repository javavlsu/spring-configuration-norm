package com.example.demo.model;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Table(name = "orders")
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    @Basic(fetch = FetchType.LAZY)
    private int id;
    @Column(name = "cipher", nullable = false, unique = true)
    @Basic(fetch = FetchType.LAZY)
    private String cipher;


    @Column(name = "product_count", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private int productCount;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cost", insertable = false)
    private double cost;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "delivery_method", nullable = false)
    private int deliveryMethodId;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "pay_method", nullable = false)
    private int payMethodId;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "client_full_name", nullable = false, insertable = false, updatable = false, length = 100)
    private String clientFullName;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "adress", nullable = false, length = 100)
    private String address;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "order_date", insertable = false)
    private Date orderDate;
    @Column(name = "order_state", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private int orderStateId;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "Comment", nullable = false, length = 100)
    private String comment;
    @JoinColumn(name = "fk_Orders_Basket1", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private DeliveryMethod deliveryMethod;
    @JoinColumn(name = "pay_method_fk", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private PayMethod payMethod;
    @JoinColumn(name = "order_state_fk", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private OrderState orderState;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="basket_fk", nullable = false)
    private Basket basket;
    @JoinColumn(name = "user_fk", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User buyer;
}
