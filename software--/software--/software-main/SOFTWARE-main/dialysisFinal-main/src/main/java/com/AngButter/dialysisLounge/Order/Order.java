package com.AngButter.dialysisLounge.Order;

import com.AngButter.dialysisLounge.Product.Product;
import com.AngButter.dialysisLounge.User.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders") // 테이블 이름을 "orders"로 변경
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public enum PaymentMethod {
        CREDIT_CARD,
        EASY_PAYMENT,
        MOBILE_PAYMENT
    }

}
