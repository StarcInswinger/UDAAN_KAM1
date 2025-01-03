package org.mihir.udaan_kam1.dto.CallTracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.Order.OrderResponse;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCResponse;
import org.mihir.udaan_kam1.model.RestaurantPOC;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTrackingResponse {
    private Long recordId;
    private RestaurantPOCResponse poc;
    private LocalDateTime callDate;
    private String notes;
    private Integer callAgain;
    private OrderResponse order;
}
