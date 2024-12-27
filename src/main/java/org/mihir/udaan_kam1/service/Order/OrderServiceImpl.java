package org.mihir.udaan_kam1.service.Order;

import org.mihir.udaan_kam1.dao.OrderRepository;
import org.mihir.udaan_kam1.dto.Order.OrderRequest;
import org.mihir.udaan_kam1.dto.Order.OrderResponse;
import org.mihir.udaan_kam1.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(modelMapper.map(order, OrderResponse.class));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id){
        Order order = orderRepository.findById(id).get();
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest){
        Order order = modelMapper.map(orderRequest, Order.class);
        Order savedOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest){
        Order order = modelMapper.map(orderRequest, Order.class);
        Order savedOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
