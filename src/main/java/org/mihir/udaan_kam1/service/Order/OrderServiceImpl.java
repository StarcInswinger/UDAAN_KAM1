package org.mihir.udaan_kam1.service.Order;

import org.mihir.udaan_kam1.dao.CallTrackingRepository;
import org.mihir.udaan_kam1.dao.OrderRepository;
import org.mihir.udaan_kam1.dao.PerformanceRepository;
import org.mihir.udaan_kam1.dto.Order.OrderRequest;
import org.mihir.udaan_kam1.dto.Order.OrderResponse;
import org.mihir.udaan_kam1.enums.RestaurantScale;
import org.mihir.udaan_kam1.model.CallTracking;
import org.mihir.udaan_kam1.model.Order;
import org.mihir.udaan_kam1.model.Performance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PerformanceRepository performanceRepository;
    private final CallTrackingRepository callTrackingRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, PerformanceRepository performanceRepository, CallTrackingRepository callTrackingRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.performanceRepository = performanceRepository;
        this.callTrackingRepository = callTrackingRepository;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(modelMapper.map(order, OrderResponse.class));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).get();
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        CallTracking callTracking = callTrackingRepository.findById(orderRequest.getRecordId()).get();
        Order order = Order.builder()
                .orderItems(orderRequest.getOrderItems())
                .orderTime(orderRequest.getOrderTime())
                .cartAmount(orderRequest.getCartAmount())
                .callTracking(callTracking)
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);
        System.out.println(savedOrder);
        addOrderToRestaurantPerformance(savedOrder);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Long orderId, OrderRequest orderRequest) {
        Order oldOrder = orderRepository.findById(orderId).get();
        if (!orderRequest.getOrderItems().equals(oldOrder.getOrderItems())) {
            oldOrder.setOrderItems(orderRequest.getOrderItems());
        }
        if (!orderRequest.getCartAmount().equals(oldOrder.getCartAmount())) {
            oldOrder.setCartAmount(orderRequest.getCartAmount());
        }
        if (!orderRequest.getOrderTime().equals(oldOrder.getOrderTime())) {
            oldOrder.setOrderTime(orderRequest.getOrderTime());
        }
        Order savedOrder = orderRepository.saveAndFlush(oldOrder);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }


    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public List<OrderResponse> getOrdersByRestaurantId(Long restaurantId) {
        List<Order> ordersByRestaurantId = orderRepository.findOrdersByCallTracking_Poc_Restaurant_RestaurantId(restaurantId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : ordersByRestaurantId) {
            orderResponses.add(modelMapper.map(order, OrderResponse.class));
        }
        return orderResponses;
    }

    private void addOrderToRestaurantPerformance(Order order) {
        Long restaurantId = order.getCallTracking().getPoc().getRestaurant().getRestaurantId();
        Performance restaurantPerformance = performanceRepository.findPerformanceByRestaurant_RestaurantId(restaurantId);
        if (restaurantPerformance == null) {
            restaurantPerformance = Performance.builder()
                    .restaurant(order.getCallTracking().getPoc().getRestaurant())
                    .lastOrderDate(order.getOrderTime())
                    .totalNumberOfOrders(1)
                    .numberOfOrdersInMonth(1)
                    .totalOrderValue(order.getCartAmount())
                    .orderValueInMonth(order.getCartAmount())
                    .build();
        } else {
            restaurantPerformance.setTotalNumberOfOrders(restaurantPerformance.getTotalNumberOfOrders() + 1);
            restaurantPerformance.setNumberOfOrdersInMonth(restaurantPerformance.getNumberOfOrdersInMonth() + 1);
            restaurantPerformance.setTotalOrderValue(restaurantPerformance.getTotalOrderValue() + order.getCartAmount());
            restaurantPerformance.setOrderValueInMonth(restaurantPerformance.getOrderValueInMonth()+ order.getCartAmount());
            restaurantPerformance.setLastOrderDate(order.getOrderTime());
        }
        restaurantPerformance.setPerformanceIndex(calculatePerformanceIndex(restaurantPerformance, order));
        performanceRepository.saveAndFlush(restaurantPerformance);

    }

    private Float calculatePerformanceIndex(Performance restaurantPerformance, Order order) {
        RestaurantScale restaurantScale = restaurantPerformance.getRestaurant().getRestaurantScale();
        Integer monthlyOrderNumber = restaurantPerformance.getNumberOfOrdersInMonth();
        float maxOrderNumber;

        Integer monthlyOrderValue = restaurantPerformance.getOrderValueInMonth();
        float maxOrderValue;

        if (restaurantScale == RestaurantScale.SMALL) {
            maxOrderValue = 20000f;
            maxOrderNumber = 10f;
        } else if (restaurantScale == RestaurantScale.MEDIUM) {
            maxOrderValue = 75000f;
            maxOrderNumber = 20f;
        } else {
            maxOrderValue = 400000f;
            maxOrderNumber = 30f;
        }
        System.out.println(restaurantScale);
        return normaliseMetrics(monthlyOrderNumber, maxOrderNumber, monthlyOrderValue, maxOrderValue);
    }

    private Float normaliseMetrics(Integer monthlyOrderNumber, Float maxOrderNumber, Integer monthlyOrderValue, Float maxOrderValue) {
        float orderScore = (monthlyOrderNumber / maxOrderNumber) * 100;
        float cartValueScore = (monthlyOrderValue / maxOrderValue) * 100;
        Float performanceIndex = (cartValueScore * 80 / 100) + (orderScore * 20 / 100);
        System.out.println(monthlyOrderNumber);
        System.out.println(maxOrderNumber);
        System.out.println(monthlyOrderValue);
        System.out.println(maxOrderValue);
        System.out.println(orderScore);
        System.out.println(cartValueScore);
        System.out.println(performanceIndex);
        return performanceIndex;
    }
}
