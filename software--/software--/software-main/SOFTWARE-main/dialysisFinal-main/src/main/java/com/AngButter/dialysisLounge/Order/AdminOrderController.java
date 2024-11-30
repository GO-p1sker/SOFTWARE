package com.AngButter.dialysisLounge.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    // 모든 주문 조회
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = adminOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order_list";
    }

    // 특정 주문 삭제
    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        adminOrderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }
}
