package org.mihir.udaan_kam1.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String orderItems;
    private Integer cartAmount;
    private String orderTime;
    private CallTrackingResponse callTracking;
}
