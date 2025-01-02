package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCallTracking_Poc_Restaurant_RestaurantId(Long restaurantId);
}
