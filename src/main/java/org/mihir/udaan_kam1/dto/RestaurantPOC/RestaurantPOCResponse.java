package org.mihir.udaan_kam1.dto.RestaurantPOC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;

import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPOCResponse {
    private Long pocId;
    private String pocName;
    private String pocContactNumber;
    private String pocRole;
    private ZoneId pocTimeZone;
    private RestaurantResponse restaurant;
}
