package org.mihir.udaan_kam1.dao;

import org.mihir.udaan_kam1.model.CallTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallTrackingRepository extends JpaRepository<CallTracking, Long> {
    CallTracking findCallTrackingByPoc_PocIdOrderByCallDateDesc(Long pocId);
    List<CallTracking> findCallTrackingsByPoc_PocId(Long pocId);
}
