package org.mihir.udaan_kam1.service.Performance;

import org.mihir.udaan_kam1.dao.PerformanceRepository;
import org.mihir.udaan_kam1.dao.RestaurantRepository;
import org.mihir.udaan_kam1.dto.Performance.PerformanceRequest;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;
import org.mihir.udaan_kam1.model.Performance;
import org.mihir.udaan_kam1.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PerformanceServiceImpl(PerformanceRepository performanceRepository, RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.performanceRepository = performanceRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PerformanceResponse> getAllPerformances(){
        List<Performance> performances = performanceRepository.findAll();
        List<PerformanceResponse> performanceResponses = new ArrayList<>();
        for (Performance performance : performances) {
            performanceResponses.add(modelMapper.map(performance, PerformanceResponse.class));
        }
        return performanceResponses;
    }

    @Override
    public PerformanceResponse getPerformanceById(Long id){
        Performance performance = performanceRepository.findById(id).get();
        return modelMapper.map(performance, PerformanceResponse.class);
    }

    @Override
    public PerformanceResponse createPerformance(PerformanceRequest performanceRequest){
        Restaurant restaurant = restaurantRepository.findById(performanceRequest.getRestaurantId()).get();
        Performance performance = modelMapper.map(performanceRequest, Performance.class);
        performance.setRestaurant(restaurant);
        Performance savedPerformance = performanceRepository.saveAndFlush(performance);
        return modelMapper.map(savedPerformance, PerformanceResponse.class);
    }

    @Override
    public PerformanceResponse updatePerformance(PerformanceRequest performanceRequest) {
        Restaurant restaurant = restaurantRepository.findById(performanceRequest.getRestaurantId()).get();
        Performance performance = modelMapper.map(performanceRequest, Performance.class);
        performance.setRestaurant(restaurant);
        Performance savedPerformance = performanceRepository.saveAndFlush(performance);
        return modelMapper.map(savedPerformance, PerformanceResponse.class);
    }

    @Override
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }

    @Override
    public PerformanceResponse getPerformanceByRestaurantId(Long restaurantId) {
        Performance performanceByRestaurantId = performanceRepository.findPerformanceByRestaurant_RestaurantId(restaurantId);
        return modelMapper.map(performanceByRestaurantId, PerformanceResponse.class);
    }


    //to implement logic

    @Override
    public List<PerformanceResponse> fetchHighPerformingAccountsByEmployeeUsername(String employeeUsername){
        List<PerformanceResponse> highPerformingAccounts = new ArrayList<>();
        return highPerformingAccounts;
    }
    @Override
    public List<PerformanceResponse> fetchLowPerformingAccountsByEmployeeUsername(String employeeUsername){
        List<PerformanceResponse> lowPerformingAccounts = new ArrayList<>();
        return lowPerformingAccounts;
    }
}
