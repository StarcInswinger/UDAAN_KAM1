package org.mihir.udaan_kam1.service.Performance;

import org.mihir.udaan_kam1.dto.Performance.PerformanceRequest;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;

import java.util.List;

public interface PerformanceService {
    List<PerformanceResponse> getAllPerformances();
    PerformanceResponse getPerformanceById(Long id);
    PerformanceResponse createPerformance(PerformanceRequest performanceRequest);
    PerformanceResponse updatePerformance(Long performanceId, PerformanceRequest performanceRequest);
    void deletePerformance(Long id);

    PerformanceResponse getPerformanceByRestaurantId(Long restaurantId);
    List<PerformanceResponse> fetchHighPerformingAccounts();
    List<PerformanceResponse> fetchLowPerformingAccounts();
}
