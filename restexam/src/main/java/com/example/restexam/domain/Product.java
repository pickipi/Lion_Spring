package com.example.restexam.domain;


import com.example.restexam.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter@Setter@NoArgsConstructor
@Table(name = "products")
@Entity
@AllArgsConstructor // DTO를 통해 값을 복사하기 위해선 전체 필드를 가져오는 생성자도 필요
@Builder
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    // DTO -> Entity 변환하는 메소드
    public static Product fromDTO(ProductDTO productDTO){
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }
}
