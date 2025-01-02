package org.mihir.udaan_kam1.service.Order;

import org.mihir.udaan_kam1.dto.Order.OrderRequest;
import org.mihir.udaan_kam1.dto.Order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(OrderRequest orderRequest);
    void deleteOrder(Long id);

    List<OrderResponse> getOrdersByRestaurantId(Long restaurantId);
}
