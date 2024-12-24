package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "order_items", nullable = false, length = 255)
    private String orderItems;

    @Column(name = "cart_amount", nullable = false)
    private Integer cartAmount;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;
}
