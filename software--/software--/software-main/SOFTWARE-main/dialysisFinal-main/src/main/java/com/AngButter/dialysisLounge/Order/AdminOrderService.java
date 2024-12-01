package com.AngButter.dialysisLounge.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminOrderService {

    private final OrderRepository orderRepository;

    // 모든 주문 조회
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 특정 주문 삭제
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
