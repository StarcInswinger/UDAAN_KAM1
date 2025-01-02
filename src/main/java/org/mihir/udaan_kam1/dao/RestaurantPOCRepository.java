package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.RestaurantPOC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantPOCRepository extends JpaRepository<RestaurantPOC, Long> {
    List<RestaurantPOC> findRestaurantPOCSByRestaurant_RestaurantId(Long restaurantId);
}
