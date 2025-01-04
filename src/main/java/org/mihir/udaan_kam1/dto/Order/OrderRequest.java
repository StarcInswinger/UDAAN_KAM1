package org.mihir.udaan_kam1.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String orderItems;
    private Integer cartAmount;
    private LocalDateTime orderTime;
    private Long recordId;
}
