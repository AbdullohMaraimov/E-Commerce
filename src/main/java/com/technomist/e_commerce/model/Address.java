package com.technomist.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 200)
    private String addressLine1;

    @Column(length = 200)
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 75)
    private String country;

    @ManyToOne(optional = false)
    private AuthUser user;
}
