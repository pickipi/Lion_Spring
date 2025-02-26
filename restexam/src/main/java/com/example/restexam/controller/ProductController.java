package com.example.restexam.controller;

import com.example.restexam.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    // 1. 상품 저장
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        long id = counter.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    // 2. 특정 상품 가져오기 (id)
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return products.get(id);
    }

    // 3. 모든 상품 가져오기
    @GetMapping
    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    // 4. 상품 수정
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        products.put(id, product);
        return product;
    }

    // 5. 상품 삭제
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        products.remove(id);
    }
}
