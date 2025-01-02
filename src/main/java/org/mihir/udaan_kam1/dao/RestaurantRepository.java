package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;
import org.mihir.udaan_kam1.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByEmployee_Username(String employeeUsername);
}
