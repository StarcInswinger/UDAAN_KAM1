package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long callId;

    @ManyToOne
    @JoinColumn(name = "poc_id", referencedColumnName = "id", nullable = false)
    private RestaurantPOC poc;

    @Column(name = "call_date", nullable = false)
    private LocalDateTime callDate;

    @Column(name = "notes", length = 255)
    private String notes;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
