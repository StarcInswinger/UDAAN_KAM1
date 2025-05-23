package org.mihir.udaan_kam1.service.CallTracking;

import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingRequest;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;

import java.util.List;

public interface CallTrackingService {
    List<CallTrackingResponse> getAllCallTracking();
    CallTrackingResponse getCallTrackingById(Long id);
    CallTrackingResponse createCallTracking(CallTrackingRequest callTrackingRequest);
    CallTrackingResponse updateCallTracking(Long callId, CallTrackingRequest callTrackingRequest);
    void deleteCallTracking(Long id);

    CallTrackingResponse getLastCallTrackingByPOCId(Long pocId);
    List<CallTrackingResponse> getAllCallTrackingsByPOCId(Long pocId);
}
