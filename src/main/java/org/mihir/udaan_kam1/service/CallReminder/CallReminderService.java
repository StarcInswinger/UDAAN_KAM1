package org.mihir.udaan_kam1.service.CallReminder;

import org.mihir.udaan_kam1.dto.CallReminder.CallReminderRequest;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderResponse;

import java.util.List;

public interface CallReminderService {
    List<CallReminderResponse> getAllCallReminders();
    CallReminderResponse getCallReminderById(Long id);
    CallReminderResponse createCallReminder(CallReminderRequest callReminderRequest);
    CallReminderResponse updateCallReminder(Long reminderId, CallReminderRequest callReminderRequest);
    void deleteCallReminder(Long id);

    List<CallReminderResponse> getCallRemindersByEmployee(String username);
}
