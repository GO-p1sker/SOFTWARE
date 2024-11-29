package com.AngButter.dialysisLounge.Order;

import com.AngButter.dialysisLounge.Product.Product;
import com.AngButter.dialysisLounge.Product.ProductRepository;
import com.AngButter.dialysisLounge.User.SiteUser;
import com.AngButter.dialysisLounge.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final UserService userService;

    // 주문 생성 페이지
    @GetMapping("/order/create")
    public String createOrder(@RequestParam("productId") Long productId, Model model) {
        // 상품 정보 가져오기
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return "redirect:/error"; // 상품이 없으면 에러 페이지로 리다이렉트
        }
        Product product = productOpt.get();

        // 로그인된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 현재 로그인한 사용자 이름
        SiteUser user = userService.getUser(username);

        // 모델에 데이터 추가
        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return "foodorder.html"; // 주문서 페이지
    }

    // 주문 제출
    @PostMapping("/order/submit")
    public String submitOrder(
            @RequestParam("productId") Long productId,
            @RequestParam("paymentMethod") String paymentMethod,
            Model model) {

        // 현재 로그인된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 로그인된 사용자 이름
        SiteUser user = userService.getUser(username); // 로그인된 사용자 정보 가져오기

        // 주문 생성
        Order order = orderService.createOrder(
                productId,
                username, // 로그인된 사용자 이름 전달
                Order.PaymentMethod.valueOf(paymentMethod)
        );

        model.addAttribute("order", order);
        return "order-end.html"; // 주문 성공 페이지로 이동 (HTML 파일명에서 .html은 생략 가능)
    }
    @GetMapping("/ingredients")
    public String ingredientsPage(Model model) {
        // 상품 목록을 데이터베이스에서 가져옵니다.
        model.addAttribute("products", productRepository.findAll());
        return "foodlist.html"; // `ingredients.html`로 이동
    }

}
