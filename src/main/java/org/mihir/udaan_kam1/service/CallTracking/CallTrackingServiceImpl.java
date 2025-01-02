package org.mihir.udaan_kam1.service.CallTracking;

import org.mihir.udaan_kam1.dao.CallTrackingRepository;
import org.mihir.udaan_kam1.dao.OrderRepository;
import org.mihir.udaan_kam1.dao.RestaurantPOCRepository;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingRequest;
import org.mihir.udaan_kam1.dto.CallTracking.CallTrackingResponse;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;
import org.mihir.udaan_kam1.model.CallTracking;
import org.mihir.udaan_kam1.model.Order;
import org.mihir.udaan_kam1.model.Performance;
import org.mihir.udaan_kam1.model.RestaurantPOC;
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

    public CallTrackingServiceImpl(RestaurantPOCRepository restaurantPOCRepository, OrderRepository orderRepository, CallTrackingRepository callTrackingRepository, ModelMapper modelMapper) {
        this.restaurantPOCRepository = restaurantPOCRepository;
        this.orderRepository = orderRepository;
        this.callTrackingRepository = callTrackingRepository;
        this.modelMapper = modelMapper;
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
                .order(order)
                .build();

        CallTracking savedCallTracking = callTrackingRepository.saveAndFlush(callTracking);
        return modelMapper.map(savedCallTracking, CallTrackingResponse.class);
    }

    @Override
    public CallTrackingResponse updateCallTracking(CallTrackingRequest callTrackingRequest) {
        RestaurantPOC restaurantPOC = restaurantPOCRepository.findById(callTrackingRequest.getPocId()).get();
        Order order = orderRepository.findById(callTrackingRequest.getOrderId()).orElse(null);
        CallTracking callTracking = modelMapper.map(callTrackingRequest, CallTracking.class);
        callTracking.setPoc(restaurantPOC);
        callTracking.setOrder(order);
        CallTracking savedCallTracking = callTrackingRepository.saveAndFlush(callTracking);
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
