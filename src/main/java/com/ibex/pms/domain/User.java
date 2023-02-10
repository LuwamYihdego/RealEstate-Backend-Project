package com.ibex.pms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToOne
    //@JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToOne
    //@JoinColumn(name = "role_id", nullable = false)
    private Role role;
    private boolean isActive = Boolean.TRUE;
    @OneToMany(mappedBy = "seller")
    private List<Property> propertyList;
}
