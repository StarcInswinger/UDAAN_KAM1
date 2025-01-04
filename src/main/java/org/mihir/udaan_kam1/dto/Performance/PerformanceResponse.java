package org.mihir.udaan_kam1.dto.Performance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceResponse {
    private Long id;
    private RestaurantResponse restaurant;
    private Integer totalOrderValue;
    private Integer totalNumberOfOrders;
    private Integer orderValueInMonth;
    private Integer numberOfOrdersInMonth;
    private Float performanceIndex;
    private String lastOrderDate;
}
