package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "positions")
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "name",insertable = true, updatable = true, length = 50,nullable = false, unique = true)
    @Basic(fetch = FetchType.LAZY)
    private String name;
    @Column(name = "description", insertable = true, updatable = true, nullable = false, length = 100)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @ManyToMany(mappedBy = "positions")
    private List<User> positions;
}
