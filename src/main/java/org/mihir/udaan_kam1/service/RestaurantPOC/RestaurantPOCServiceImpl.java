package org.mihir.udaan_kam1.service.RestaurantPOC;

import org.mihir.udaan_kam1.dao.RestaurantPOCRepository;
import org.mihir.udaan_kam1.dao.RestaurantRepository;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCRequest;
import org.mihir.udaan_kam1.dto.RestaurantPOC.RestaurantPOCResponse;
import org.mihir.udaan_kam1.exception.ResourceNotFoundException;
import org.mihir.udaan_kam1.model.Restaurant;
import org.mihir.udaan_kam1.model.RestaurantPOC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantPOCServiceImpl implements RestaurantPOCService {
    private final RestaurantPOCRepository restaurantPOCRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantPOCServiceImpl(RestaurantPOCRepository restaurantPOCRepository, RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantPOCRepository = restaurantPOCRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantPOCResponse> getAllRestaurantPOCs() {
        List<RestaurantPOC> restaurantPOCs = restaurantPOCRepository.findAll();
        List<RestaurantPOCResponse> restaurantPOCResponses = new ArrayList<>();
        for (RestaurantPOC restaurantPOC : restaurantPOCs) {
            restaurantPOCResponses.add(modelMapper.map(restaurantPOC, RestaurantPOCResponse.class));
        }
        return restaurantPOCResponses;
    }

    @Override
    public RestaurantPOCResponse getRestaurantPOC(Long id) {
        RestaurantPOC restaurantPOC = restaurantPOCRepository.findById(id).get();
        return modelMapper.map(restaurantPOC, RestaurantPOCResponse.class);
    }

    @Override
    public RestaurantPOCResponse createRestaurantPOC(RestaurantPOCRequest restaurantPOCRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantPOCRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantPOCRequest.getRestaurantId()));
        RestaurantPOC poc = RestaurantPOC.builder()
                .pocName(restaurantPOCRequest.getPocName())
                .pocContactNumber(restaurantPOCRequest.getPocContactNumber())
                .pocRole(restaurantPOCRequest.getPocRole())
                .pocTimeZone(restaurantPOCRequest.getPocTimeZone())
                .restaurant(restaurant)
                .build();

        RestaurantPOC savedRestaurantPOC = restaurantPOCRepository.saveAndFlush(poc);
        return modelMapper.map(savedRestaurantPOC, RestaurantPOCResponse.class);
    }

    @Override
    public RestaurantPOCResponse updateRestaurantPOC(Long pocId, RestaurantPOCRequest restaurantPOCRequest) {
        RestaurantPOC oldRestaurantPOC = restaurantPOCRepository.findById(pocId).get();
        if(!restaurantPOCRequest.getPocName().equals(oldRestaurantPOC.getPocName())){
            oldRestaurantPOC.setPocName(restaurantPOCRequest.getPocName());
        }
        if(!restaurantPOCRequest.getPocContactNumber().equals(oldRestaurantPOC.getPocContactNumber())){
            oldRestaurantPOC.setPocContactNumber(restaurantPOCRequest.getPocContactNumber());
        }
        if(!restaurantPOCRequest.getPocRole().equals(oldRestaurantPOC.getPocRole())){
            oldRestaurantPOC.setPocRole(restaurantPOCRequest.getPocRole());
        }
        if(!restaurantPOCRequest.getPocTimeZone().equals(oldRestaurantPOC.getPocTimeZone())){
            oldRestaurantPOC.setPocTimeZone(restaurantPOCRequest.getPocTimeZone());
        }
        RestaurantPOC savedRestaurantPOC = restaurantPOCRepository.saveAndFlush(oldRestaurantPOC);
        return modelMapper.map(savedRestaurantPOC, RestaurantPOCResponse.class);
    }

    @Override
    public void deleteRestaurantPOC(Long id) {
        restaurantPOCRepository.deleteById(id);
    }

    @Override
    public List<RestaurantPOCResponse> getAllRestaurantPOCsByRestaurantId(Long restaurantId) {
        List<RestaurantPOC> restaurantPOCsByRestaurantId = restaurantPOCRepository.findRestaurantPOCSByRestaurant_RestaurantId(restaurantId);
        List<RestaurantPOCResponse> restaurantPOCResponses = new ArrayList<>();
        for(RestaurantPOC restaurantPOC : restaurantPOCsByRestaurantId) {
            restaurantPOCResponses.add(modelMapper.map(restaurantPOC, RestaurantPOCResponse.class));
        }
        return restaurantPOCResponses;
    }
}
