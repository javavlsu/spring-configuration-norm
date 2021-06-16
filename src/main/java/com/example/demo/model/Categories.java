package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "categories")
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Category", nullable = false)
    private String name;

    private String description;

}
