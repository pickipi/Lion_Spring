package com.example.restexam.dto;

import com.example.restexam.domain.Product;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    private String name;
    private double price;

    // Entity -> DTO 변환 메소드
    public static ProductDTO fromEntity(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
