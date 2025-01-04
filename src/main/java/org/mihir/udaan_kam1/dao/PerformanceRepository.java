package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    Performance findPerformanceByRestaurant_RestaurantId(Long restaurantId);
    List<Performance> findPerformancesByRestaurant_Employee_UsernameOrderByPerformanceIndexDesc(String employeeUsername);
    List<Performance> findPerformancesByRestaurant_Employee_UsernameOrderByPerformanceIndexAsc(String employeeUsername);
}
