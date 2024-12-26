package org.mihir.udaan_kam1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.RestaurantScale;
import org.mihir.udaan_kam1.enums.RestaurantStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    private Long id;
    private String restaurantName;
    private String restaurantAddress;
    private RestaurantScale restaurantScale;
    private RestaurantStatus restaurantStatus;
    private EmployeeResponse employee;
}
