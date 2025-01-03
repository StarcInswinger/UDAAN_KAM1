package org.mihir.udaan_kam1.dto.CallReminder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.CallReminderStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallReminderRequest {
    private Long pocId;
    private LocalDateTime callAgainDate;
    private CallReminderStatus callReminderStatus;
}
