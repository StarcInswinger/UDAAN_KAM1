package org.mihir.udaan_kam1.service.CallTracking;

import org.mihir.udaan_kam1.dao.CallReminderRepository;
import org.mihir.udaan_kam1.dao.CallTrackingRepository;
import org.mihir.udaan_kam1.dao.OrderRepository;
import org.mihir.udaan_kam1.dao.RestaurantPOCRepository;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingRequest;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;
import org.mihir.udaan_kam1.enums.CallReminderStatus;
import org.mihir.udaan_kam1.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallTrackingServiceImpl implements CallTrackingService {
    private final RestaurantPOCRepository restaurantPOCRepository;
    private final OrderRepository orderRepository;
    private final CallTrackingRepository callTrackingRepository;
    private final ModelMapper modelMapper;
    private final CallReminderRepository callReminderRepository;

    public CallTrackingServiceImpl(RestaurantPOCRepository restaurantPOCRepository, OrderRepository orderRepository, CallTrackingRepository callTrackingRepository, ModelMapper modelMapper, CallReminderRepository callReminderRepository) {
        this.restaurantPOCRepository = restaurantPOCRepository;
        this.orderRepository = orderRepository;
        this.callTrackingRepository = callTrackingRepository;
        this.modelMapper = modelMapper;
        this.callReminderRepository = callReminderRepository;
    }

    @Override
    public List<CallTrackingResponse> getAllCallTracking() {
        List<CallTracking> callTrackings = callTrackingRepository.findAll();
        List<CallTrackingResponse> callTrackingResponses = new ArrayList<>();
        for (CallTracking callTracking : callTrackings) {
            callTrackingResponses.add(modelMapper.map(callTracking, CallTrackingResponse.class));
        }
        return callTrackingResponses;
    }

    @Override
    public CallTrackingResponse getCallTrackingById(Long id) {
        CallTracking callTracking = callTrackingRepository.findById(id).get();
        return modelMapper.map(callTracking, CallTrackingResponse.class);
    }

    @Override
    public CallTrackingResponse createCallTracking(CallTrackingRequest callTrackingRequest) {
        Order order = null;
        RestaurantPOC restaurantPOC = restaurantPOCRepository.findById(callTrackingRequest.getPocId()).get();

        if(callTrackingRequest.getOrderId()!=null) {
            order = orderRepository.findById(callTrackingRequest.getOrderId()).orElse(null);
        }

        CallTracking callTracking = CallTracking.builder()
                .poc(restaurantPOC)
                .callDate(callTrackingRequest.getCallDate())
                .notes(callTrackingRequest.getNotes())
                .callAgain(callTrackingRequest.getCallAgain())
                .build();

        CallReminder callReminder = CallReminder.builder()
                .callReminderStatus(CallReminderStatus.TO_CALL)
                .restaurantPOC(restaurantPOC)
                .callAgainDate(callTracking.getCallBackDate())
                .build();

        callReminderRepository.save(callReminder);
        CallTracking savedCallTracking = callTrackingRepository.saveAndFlush(callTracking);
        System.out.println(modelMapper.map(savedCallTracking, CallTrackingResponse.class));
        return modelMapper.map(savedCallTracking, CallTrackingResponse.class);
    }

    @Override
    public CallTrackingResponse updateCallTracking(Long callId, CallTrackingRequest callTrackingRequest) {
        CallTracking oldCallTracking = callTrackingRepository.findById(callId).get();
        if (!callTrackingRequest.getCallDate().equals(oldCallTracking.getCallDate())) {
            oldCallTracking.setCallDate(callTrackingRequest.getCallDate());
        }
        if (callTrackingRequest.getNotes() != null && !callTrackingRequest.getNotes().equals(oldCallTracking.getNotes())) {
            oldCallTracking.setNotes(callTrackingRequest.getNotes());
        }
        if (callTrackingRequest.getCallAgain() != null && !callTrackingRequest.getCallAgain().equals(oldCallTracking.getCallAgain())) {
            oldCallTracking.setCallAgain(callTrackingRequest.getCallAgain());
        }
        if (callTrackingRequest.getOrderId() != null && !callTrackingRequest.getOrderId().equals(oldCallTracking.getOrderId())) {
            oldCallTracking.setOrderId(callTrackingRequest.getOrderId());
        }
        CallTracking savedCallTracking = callTrackingRepository.saveAndFlush(oldCallTracking);
        return modelMapper.map(savedCallTracking, CallTrackingResponse.class);
    }


    @Override
    public void deleteCallTracking(Long id) {
        callTrackingRepository.deleteById(id);
    }



    @Override
    public CallTrackingResponse getLastCallTrackingByPOCId(Long pocId) {
        CallTracking lastCallTracking = callTrackingRepository.findCallTrackingByPoc_PocIdOrderByCallDateDesc(pocId);
        return modelMapper.map(lastCallTracking, CallTrackingResponse.class);
    }

    @Override
    public List<CallTrackingResponse> getAllCallTrackingsByPOCId(Long pocId) {
        List<CallTracking> callTrackingsByPocId = callTrackingRepository.findCallTrackingsByPoc_PocId(pocId);
        List<CallTrackingResponse> callTrackingResponses = new ArrayList<>();
        for (CallTracking callTracking : callTrackingsByPocId) {
            callTrackingResponses.add(modelMapper.map(callTracking, CallTrackingResponse.class));
        }
        return callTrackingResponses;
    }
}
