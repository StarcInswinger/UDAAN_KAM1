package org.mihir.udaan_kam1.service.CallReminder;

import org.mihir.udaan_kam1.dao.CallReminderRepository;
import org.mihir.udaan_kam1.dao.RestaurantPOCRepository;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderRequest;
import org.mihir.udaan_kam1.dto.CallReminder.CallReminderResponse;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;
import org.mihir.udaan_kam1.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallReminderServiceImpl implements CallReminderService{
    private CallReminderRepository callReminderRepository;
    private RestaurantPOCRepository restaurantPOCRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CallReminderServiceImpl(CallReminderRepository callReminderRepository, RestaurantPOCRepository restaurantPOCRepository, ModelMapper modelMapper) {
        this.callReminderRepository = callReminderRepository;
        this.restaurantPOCRepository = restaurantPOCRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<CallReminderResponse> getAllCallReminders() {
        List<CallReminder> callReminders = callReminderRepository.findAll();
        List<CallReminderResponse> callReminderResponses = new ArrayList<>();
        for (CallReminder callReminder : callReminders) {
            callReminderResponses.add(modelMapper.map(callReminder, CallReminderResponse.class));
        }
        return callReminderResponses;
    }

    @Override
    public CallReminderResponse getCallReminderById(Long id) {
        CallReminder callReminder = callReminderRepository.findById(id).get();
        return modelMapper.map(callReminder, CallReminderResponse.class);
    }

    @Override
    public CallReminderResponse createCallReminder(CallReminderRequest callReminderRequest) {
        RestaurantPOC restaurantPOC = restaurantPOCRepository.findById(callReminderRequest.getPocId()).get();
        CallReminder callReminder = modelMapper.map(callReminderRequest, CallReminder.class);
        callReminder.setRestaurantPOC(restaurantPOC);
        CallReminder savedCallReminder = callReminderRepository.saveAndFlush(callReminder);
        return modelMapper.map(savedCallReminder, CallReminderResponse.class);
    }

    @Override
    public CallReminderResponse updateCallReminder(CallReminderRequest callReminderRequest) {
        RestaurantPOC restaurantPOC = restaurantPOCRepository.findById(callReminderRequest.getPocId()).get();
        CallReminder callReminder = modelMapper.map(callReminderRequest, CallReminder.class);
        callReminder.setRestaurantPOC(restaurantPOC);
        CallReminder savedCallReminder = callReminderRepository.saveAndFlush(callReminder);
        return modelMapper.map(savedCallReminder, CallReminderResponse.class);
    }

    @Override
    public void deleteCallReminder(Long id) {
        callReminderRepository.deleteById(id);
    }
}
