package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "suppler_companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @Basic(fetch = FetchType.LAZY)
    private int id;
    @Column(name = "name", length = 50, updatable = false, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description", length = 100, nullable = false, updatable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @Column(name = "adress", length = 100, nullable = false, updatable = true)
    @Basic(fetch = FetchType.LAZY)
    private String address;
    @Column(name = "rating", nullable = true, updatable = false)
    private double rating;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "supplerCompany")
    private List<Product> products;


    public SupplerCompany(String name, String address, String description, double rat) {
        this.name=name;
        this.description = description;
        this.address = address;
        this.rating = rat;
    }
}
