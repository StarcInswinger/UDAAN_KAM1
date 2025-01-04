package org.mihir.udaan_kam1.service.Performance;

import org.mihir.udaan_kam1.dao.PerformanceRepository;
import org.mihir.udaan_kam1.dao.RestaurantRepository;
import org.mihir.udaan_kam1.dto.Performance.PerformanceRequest;
import org.mihir.udaan_kam1.dto.Performance.PerformanceResponse;
import org.mihir.udaan_kam1.model.Performance;
import org.mihir.udaan_kam1.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    public PerformanceResponse updatePerformance(Long performanceId, PerformanceRequest performanceRequest) {
        Performance oldPerformance = performanceRepository.findById(performanceId).get();
        if (!performanceRequest.getTotalOrderValue().equals(oldPerformance.getTotalOrderValue())) {
            oldPerformance.setTotalOrderValue(performanceRequest.getTotalOrderValue());
        }
        if (!performanceRequest.getTotalNumberOfOrders().equals(oldPerformance.getTotalNumberOfOrders())) {
            oldPerformance.setTotalNumberOfOrders(performanceRequest.getTotalNumberOfOrders());
        }
        if (!performanceRequest.getOrderValueInMonth().equals(oldPerformance.getOrderValueInMonth())) {
            oldPerformance.setOrderValueInMonth(performanceRequest.getOrderValueInMonth());
        }
        if (!performanceRequest.getNumberOfOrdersInMonth().equals(oldPerformance.getNumberOfOrdersInMonth())) {
            oldPerformance.setNumberOfOrdersInMonth(performanceRequest.getNumberOfOrdersInMonth());
        }
        if (!performanceRequest.getPerformanceIndex().equals(oldPerformance.getPerformanceIndex())) {
            oldPerformance.setPerformanceIndex(performanceRequest.getPerformanceIndex());
        }
        if (!performanceRequest.getLastOrderDate().equals(oldPerformance.getLastOrderDate())) {
            oldPerformance.setLastOrderDate(performanceRequest.getLastOrderDate());
        }
        if (!performanceRequest.getRestaurantId().equals(oldPerformance.getRestaurant().getRestaurantId())) {
            Restaurant restaurant = restaurantRepository.findById(performanceRequest.getRestaurantId()).get();
            oldPerformance.setRestaurant(restaurant);
        }
        Performance savedPerformance = performanceRepository.saveAndFlush(oldPerformance);
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


    @Override
    public List<PerformanceResponse> fetchHighPerformingAccountsByEmployeeUsername(String employeeUsername){
        List<Performance> highPerformances = performanceRepository.findPerformancesByRestaurant_Employee_UsernameOrderByPerformanceIndexDesc(employeeUsername);
        List<PerformanceResponse> highPerformingAccounts = new ArrayList<>();
        for(Performance performance : highPerformances){
            highPerformingAccounts.add(modelMapper.map(performance, PerformanceResponse.class));
        }
        return highPerformingAccounts;
    }
    @Override
    public List<PerformanceResponse> fetchLowPerformingAccountsByEmployeeUsername(String employeeUsername){
        List<Performance> lowPerformances = performanceRepository.findPerformancesByRestaurant_Employee_UsernameOrderByPerformanceIndexAsc(employeeUsername);
        List<PerformanceResponse> lowPerformingAccounts = new ArrayList<>();
        for(Performance performance : lowPerformances){
            lowPerformingAccounts.add(modelMapper.map(performance, PerformanceResponse.class));
        }
        return lowPerformingAccounts;
    }

    @Override
    public void resetMonthlyPerformanceMetrics() {
        System.out.println("trigger");
        List<Performance> performances = performanceRepository.findAll();
        for (Performance performance : performances) {
            performance.setOrderValueInMonth(0);
            performance.setNumberOfOrdersInMonth(0);
        }
        performanceRepository.saveAll(performances);
    }
}
