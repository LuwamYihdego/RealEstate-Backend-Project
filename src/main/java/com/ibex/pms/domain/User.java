package com.ibex.pms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    private String email;
    private String password;

    @OneToOne
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    private UserDetails userDetails;

    private boolean isActive = Boolean.TRUE;

    private boolean isDeleted = Boolean.FALSE;

}
