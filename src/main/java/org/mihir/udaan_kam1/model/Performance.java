package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "performances")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "restaurant_id",  referencedColumnName = "id")
    private Restaurant restaurant;

    @Column(name = "total_order_value")
    private Float totalOrderValue;

    @Column(name = "number_of_orders")
    private Integer totalNumberOfOrders;

    @Column(name = "order_frequency")
    private Integer numberOfOrdersLastMonth;

    @Column(name = "performance_index")
    private Float performanceIndex;

    @Column(name = "last_order_date")
    private LocalDateTime lastOrderDate;
}
