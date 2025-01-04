package org.mihir.udaan_kam1.controller;

import org.mihir.udaan_kam1.dto.CallReminder.CallReminderRequest;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderResponse;
import org.mihir.udaan_kam1.service.CallReminder.CallReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/call-reminders")
public class CallReminderController {

    private final CallReminderService callReminderService;

    @Autowired
    public CallReminderController(CallReminderService callReminderService) {
        this.callReminderService = callReminderService;
    }

    @GetMapping
    public ResponseEntity<List<CallReminderResponse>> getCallReminders() {
        return ResponseEntity.ok(callReminderService.getAllCallReminders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallReminderResponse> getCallReminderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(callReminderService.getCallReminderById(id));
    }

    @PostMapping
    public ResponseEntity<CallReminderResponse> createCallReminder(@RequestBody CallReminderRequest callReminderRequest) {
        return ResponseEntity.ok(callReminderService.createCallReminder(callReminderRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CallReminderResponse> updateCallTracking(@PathVariable(name = "id") Long reminderId, @RequestBody CallReminderRequest callReminderRequest) {
        return ResponseEntity.ok(callReminderService.updateCallReminder(reminderId, callReminderRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCallReminder(@PathVariable("id") Long id) {
        callReminderService.deleteCallReminder(id);
        return ResponseEntity.ok("Call Reminder deleted with ID: " + id);
    }


    @GetMapping("/by-employee/{username}")
    public ResponseEntity<List<CallReminderResponse>> getCallRemindersByEmployee(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(callReminderService.getCallRemindersByEmployee(username));
    }
}
