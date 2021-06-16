package com.example.demo.model;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "deliverymethods")
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "delivery_method", length = 50)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description", length = 100)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryMethod")
    private List<Order> order;

    public DeliveryMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Bean
    public DeliveryMethod getSingle(){
        return this;
    }
}

