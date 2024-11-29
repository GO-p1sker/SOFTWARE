package com.AngButter.dialysisLounge.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  // Lombok의 @Data 어노테이션을 사용하여 Getter, Setter, toString, equals, hashCode 메서드를 자동으로 생성
@Builder // 빌더 패턴 사용을 위해 추가
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
@Table(name = "product") // DB 테이블명을 지정
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String description;
    private String imageUrl;
}
