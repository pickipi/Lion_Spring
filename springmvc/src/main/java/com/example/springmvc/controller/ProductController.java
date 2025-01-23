package com.example.springmvc.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    final int MONEY_FORMATT_KOREA = 1000;

    private List<Product> products = Arrays.asList(
            new Product(1, "Apple", 1.20*MONEY_FORMATT_KOREA),
            new Product(2, "Banana", 2.50*MONEY_FORMATT_KOREA),
            new Product(3, "Cherry", 3.33*MONEY_FORMATT_KOREA)
    );

    @GetMapping("/products")
    public String showProducts(Model model){
        model.addAttribute("titleText", "-----상품 페이지-----");
        model.addAttribute("products", products);
        return "products";
    }

    @Getter
    @AllArgsConstructor
    static class Product{
        private int id;
        private String name;
        private double price;
    }
}
