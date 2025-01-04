package org.mihir.udaan_kam1.service.Restaurant;

import org.mihir.udaan_kam1.dao.EmployeeRepository;
import org.mihir.udaan_kam1.dao.RestaurantRepository;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantResponse;
import org.mihir.udaan_kam1.dto.Restaurant.RestaurantRequest;
import org.mihir.udaan_kam1.model.Employee;
import org.mihir.udaan_kam1.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Restaurant savedRestaurant = restaurantRepository.saveAndFlush(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantResponse.class);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant oldRestaurant = restaurantRepository.findById(id).get();
        if (!restaurantRequest.getRestaurantName().equals(oldRestaurant.getRestaurantName())) {
            oldRestaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        }
        if (!restaurantRequest.getRestaurantAddress().equals(oldRestaurant.getRestaurantAddress())) {
            oldRestaurant.setRestaurantAddress(restaurantRequest.getRestaurantAddress());
        }
        if (!restaurantRequest.getRestaurantScale().equals(oldRestaurant.getRestaurantScale())) {
            oldRestaurant.setRestaurantScale(restaurantRequest.getRestaurantScale());
        }
        if (!restaurantRequest.getRestaurantStatus().equals(oldRestaurant.getRestaurantStatus())) {
            oldRestaurant.setRestaurantStatus(restaurantRequest.getRestaurantStatus());
        }
        if (!restaurantRequest.getEmployeeUsername().equals(oldRestaurant.getEmployee().getUsername())) {
            Employee employee = employeeRepository.findByUsername(restaurantRequest.getEmployeeUsername()).get();
            oldRestaurant.setEmployee(employee);
        }
        Restaurant savedRestaurant = restaurantRepository.saveAndFlush(oldRestaurant);
        return modelMapper.map(savedRestaurant, RestaurantResponse.class);
    }


    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<RestaurantResponse> getRestaurantsByEmployeeUsername(String employeeUsername) {
        List<Restaurant> restaurants = restaurantRepository.findAllByEmployee_Username(employeeUsername);
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();

        for(Restaurant restaurant : restaurants){
            restaurantResponses.add(modelMapper.map(restaurant, RestaurantResponse.class));
        }
        return restaurantResponses;
    }
}
