package com.ibex.pms.domain;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Role role;
    private Address address;
    private List<Property> propertyList;
}
