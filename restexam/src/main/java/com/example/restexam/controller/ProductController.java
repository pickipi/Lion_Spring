package com.example.restexam.controller;

import com.example.restexam.dto.ProductDTO;
import com.example.restexam.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 1. 상품 저장
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        log.info(productDTO.getName());
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    // 2. 특정 상품 가져오기 (id)
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // 3. 모든 상품 가져오기
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    // 4. 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        productDTO.setId(id); // productDTO를 전달하기 전에 id부터 받아온 값으로 변경한다.
        return ResponseEntity.ok(productService.updateProduct(productDTO));
    }

    // 5. 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(id + " 상품이 삭제 되었습니다.");
        // 만약 <Void> 이면 noContent().build();를 넣어서 보낸다.
    }
}
