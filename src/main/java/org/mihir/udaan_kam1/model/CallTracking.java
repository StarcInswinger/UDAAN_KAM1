package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "call_tracking")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "poc_id", referencedColumnName = "id", nullable = false)
    private RestaurantPOC poc;

    @Column(name = "call_date", nullable = false)
    private LocalDateTime callDate;

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "call_again")
    private Integer callAgain;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public LocalDateTime getCallBackDate() {
        if (callAgain == null || callAgain < 0) {
            return null;
        }
        return callDate.plusDays(callAgain);
    }
}
