package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.RestaurantPOC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantPOCRepository extends JpaRepository<RestaurantPOC, Long> {

}
