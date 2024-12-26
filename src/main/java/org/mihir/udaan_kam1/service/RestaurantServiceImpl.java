package org.mihir.udaan_kam1.service;

import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dao.RestaurantRepository;
import org.mihir.udaan_kam1.dto.EmployeeRequest;
import org.mihir.udaan_kam1.dto.RestaurantResponse;
import org.mihir.udaan_kam1.dto.RestaurantRequest;
import org.mihir.udaan_kam1.model.Employee;
import org.mihir.udaan_kam1.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantResponses.add(modelMapper.map(restaurant, RestaurantResponse.class));
        }
        return restaurantResponses;
    }

    @Override
    public RestaurantResponse getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        return modelMapper.map(restaurant, RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        Employee employee = employeeRepository.findByUsername(restaurantRequest.getEmployeeUsername()).get();
        Restaurant restaurant = modelMapper.map(restaurantRequest, Restaurant.class);
        restaurant.setEmployee(employee);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse updateRestaurant(RestaurantRequest restaurantRequest) {
        Employee employee = employeeRepository.findByUsername(restaurantRequest.getEmployeeUsername()).get();
        Restaurant restaurant = modelMapper.map(restaurantRequest, Restaurant.class);
        restaurant.setEmployee(employee);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantResponse.class);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
