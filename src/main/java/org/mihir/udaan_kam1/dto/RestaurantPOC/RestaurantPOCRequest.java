package org.mihir.udaan_kam1.dto.RestaurantPOC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantPOCRequest {
    private String pocName;
    private String pocContactNumber;
    private String pocRole;
    private ZoneId pocTimeZone;
    private Long restaurantId;
}
