package org.mihir.udaan_kam1.dto.CallTracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTrackingRequest {
    private Long pocId;
    private LocalDateTime callDate;
    private String notes;
    private Integer callAgain;
    private Long OrderId;
}
