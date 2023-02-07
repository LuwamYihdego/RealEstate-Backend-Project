package com.ibex.pms.domain;

public enum Status {
    AVAILABE(1),
    PENDING(2),
    CONTINGENT(3),
    SOLD(4);
    private int value;
    private Status(int val){
        this.value = val;
    }
}
