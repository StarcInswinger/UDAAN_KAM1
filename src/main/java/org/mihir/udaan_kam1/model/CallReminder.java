package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.CallReminderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "call_reminder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallReminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long reminderId;

    @ManyToOne
    @JoinColumn(name = "poc_id", referencedColumnName = "id", nullable = false)
    private RestaurantPOC restaurantPOC;

    @Column(name = "call_again_date", nullable = false)
    private LocalDateTime callAgainDate;

    @Column(name = "call_reminder_status", nullable = false)
    private CallReminderStatus callReminderStatus;
}
