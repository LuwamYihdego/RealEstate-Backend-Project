package com.ibex.pms.repository;

import com.ibex.pms.domain.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PropertySearchDao {
private final EntityManager em;
public List<Property> findAllBySimpleQuery(double price, int lotSize, int numberOfBedRooms, int numberOfBaths){

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);

    Root<Property>  root =  criteriaQuery.from(Property.class);


    Predicate pricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);

    Predicate lotSizePredicate = criteriaBuilder.equal(root.get("lotSize"), lotSize);

    Predicate numberOfBedRoomsPredicate = criteriaBuilder.equal(root.get("numberOfBedRooms"), numberOfBedRooms);

    Predicate numberOfBathsPredicate = criteriaBuilder.equal(root.get("numberOfBaths"), numberOfBaths);

   Predicate andPredicate =  criteriaBuilder.or(pricePredicate,lotSizePredicate,numberOfBedRoomsPredicate,numberOfBathsPredicate );

   criteriaQuery.where(andPredicate);

    TypedQuery<Property> query = em.createQuery(criteriaQuery);


    return query.getResultList();
}

}
