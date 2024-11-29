package com.AngButter.dialysisLounge.Order;

import com.AngButter.dialysisLounge.Product.Product;
import com.AngButter.dialysisLounge.Product.ProductRepository;
import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    // 주문 생성 메서드
    public Order createOrder(Long productId, String username, Order.PaymentMethod paymentMethod) {
        // 상품 가져오기
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid product ID: " + productId);
        }
        Product product = productOpt.get();

        // 사용자 가져오기
        SiteUser user = userService.getUser(username);

        // 주문 생성
        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setTotalPrice(product.getPrice());
        order.setPaymentMethod(paymentMethod);

        // 주문 저장
        return orderRepository.save(order);
    }
}
