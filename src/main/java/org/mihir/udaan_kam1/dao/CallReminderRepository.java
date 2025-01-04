package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.CallReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CallReminderRepository extends JpaRepository<CallReminder, Long> {
    List<CallReminder> findByRestaurantPOC_Restaurant_Employee_UsernameAndCallAgainDateBetweenOrderByCallAgainDateDesc(String username, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
