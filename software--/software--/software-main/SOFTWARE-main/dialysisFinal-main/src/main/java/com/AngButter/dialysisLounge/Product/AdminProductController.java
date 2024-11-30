package com.AngButter.dialysisLounge.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 모든 상품 목록 조회
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", adminProductService.getAllProducts());
        return "admin/product_list";
    }

    // 상품 추가 폼
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product_form";
    }

    // 상품 추가 처리
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        adminProductService.addProduct(product);
        return "redirect:/admin/products";
    }

    // 상품 수정 폼
    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", adminProductService.getProductById(id));
        return "admin/product_form";
    }

    // 상품 수정 처리
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        adminProductService.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    // 상품 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
