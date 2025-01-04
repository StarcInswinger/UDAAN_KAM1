package org.mihir.udaan_kam1.dto.Performance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.model.Restaurant;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceRequest {
    private Long restaurantId;
    private Float totalOrderValue;
    private Integer totalNumberOfOrders;
    private Integer numberOfOrdersLastMonth;
    private Float performanceIndex;
    private LocalDateTime lastOrderDate;
}
