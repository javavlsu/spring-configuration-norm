package com.example.demo.model;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "users")
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "cardcipher", length = 60, updatable = true, nullable = false, insertable = true, unique = true)
    @Basic(fetch = FetchType.LAZY)
    private String cardCipher;

    @Column(name = "password", length = 50, nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String password;
    @Column(name = "first_name", length = 50,  updatable = true, nullable = false, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String firstName;
    @Column(name = "second_name", length = 50, nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String secondName;
    @Column(name = "middle_name", length = 50, nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String middleName;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "adress", length = 100, nullable = false, updatable = true, insertable = true)
    private String address;
    @Column(name = "phone_number", length = 13,  nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String phoneNumber;
    @Column(name = "email", length = 30, nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String emailAddress;
    @Column(name = "descriprion", length = 100, nullable = false, updatable = true, insertable = true)
    @Basic(fetch = FetchType.LAZY)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "temptable", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "position_fk")})
    private List<Position> positions;
    //@OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "buyer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Basket> basket;
    //@OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "buyer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> order;

}
