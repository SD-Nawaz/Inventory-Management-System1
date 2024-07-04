package com.example.Inventory.Management.System1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Inventory.Management.System1.entity.Order;
import com.example.Inventory.Management.System1.entity.OrderItem;
import com.example.Inventory.Management.System1.entity.Stock;
import com.example.Inventory.Management.System1.repository.OrderRepository;
import com.example.Inventory.Management.System1.repository.StockRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            Stock stock = stockRepository.findByName(item.getProductName());
            if (stock != null) {
                stock.setQuantity(stock.getQuantity() - item.getQuantity());
                stockRepository.save(stock);
            }
        }
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setCustomerName(orderDetails.getCustomerName());
            order.setOrderDate(orderDetails.getOrderDate());
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setItems(orderDetails.getItems());
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            for (OrderItem item : order.getItems()) {
                Stock stock = stockRepository.findByName(item.getProductName());
                if (stock != null) {
                    stock.setQuantity(stock.getQuantity() + item.getQuantity());
                    stockRepository.save(stock);
                }
            }
        }
        orderRepository.deleteById(id);
    }
}
