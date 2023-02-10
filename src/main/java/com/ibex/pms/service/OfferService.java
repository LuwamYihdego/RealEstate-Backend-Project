package com.ibex.pms.service;

import com.ibex.pms.domain.Offer;
import com.ibex.pms.domain.dto.OfferRequestDto;
import com.ibex.pms.domain.dto.OfferResponseDto;

import java.util.List;

public interface OfferService {
    List<OfferResponseDto> getAll();
    OfferResponseDto getById(long id);
    void deleteById(long id);
    void  save(OfferRequestDto offer);
    void  update(long id, OfferRequestDto offer);
}
