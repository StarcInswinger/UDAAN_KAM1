package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.CallReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallReminderRepository extends JpaRepository<CallReminder, Long> {
}
