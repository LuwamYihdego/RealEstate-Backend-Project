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
    private Address address;
    @OneToOne
    private Role role;
//    @OneToOne(fetch = FetchType.LAZY)
//    private UserDetails userDetails;
    private boolean isActive = Boolean.TRUE;
    private boolean isDeleted = Boolean.FALSE;
    @OneToMany(mappedBy = "seller")
    private List<Property> propertyList;
}
