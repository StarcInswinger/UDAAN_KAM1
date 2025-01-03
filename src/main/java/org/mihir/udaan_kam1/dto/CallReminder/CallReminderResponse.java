package org.mihir.udaan_kam1.dto.CallReminder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCResponse;
import org.mihir.udaan_kam1.enums.CallReminderStatus;
import org.mihir.udaan_kam1.model.RestaurantPOC;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallReminderResponse {
    private Long reminderId;
    private RestaurantPOCResponse poc;
    private LocalDateTime callAgainDate;
    private CallReminderStatus callReminderStatus;
}
