package org.mihir.udaan_kam1.dto;

import lombok.*;
import org.mihir.udaan_kam1.enums.RestaurantScale;
import org.mihir.udaan_kam1.enums.RestaurantStatus;
import org.mihir.udaan_kam1.model.Employee;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
    private String restaurantName;
    private String restaurantAddress;
    private RestaurantScale restaurantScale;
    private RestaurantStatus restaurantStatus;
    private String employeeUsername;
}
