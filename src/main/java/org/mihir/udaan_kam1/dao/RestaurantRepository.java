package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
