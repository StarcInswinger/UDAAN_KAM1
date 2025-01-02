package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "order_items", nullable = false)
    private String orderItems;

    @Column(name = "cart_amount", nullable = false)
    private Integer cartAmount;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @OneToOne
    private CallTracking callTracking;
}
