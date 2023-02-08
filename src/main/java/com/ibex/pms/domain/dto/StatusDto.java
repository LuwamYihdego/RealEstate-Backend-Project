package com.ibex.pms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum StatusDto {
    AVAILABE(1),
    PENDING(2),
    CONTINGENT(3),
    SOLD(4);
    private int value;
    private StatusDto(int val){
        this.value = val;
    }
}
