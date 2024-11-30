package com.AngButter.dialysisLounge.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "foodlist";
    }
    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id); // 상품 ID로 상세정보 가져오기
        model.addAttribute("product", product);
        return "food"; // food.html을 렌더링
    }



}
