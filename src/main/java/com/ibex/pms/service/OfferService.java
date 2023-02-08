package com.ibex.pms.service;

import com.ibex.pms.domain.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAll();
    Offer getById(long id);
    void deleteById(long id);
    void  save(Offer offer);
    void  update(long id, Offer offer);
}
