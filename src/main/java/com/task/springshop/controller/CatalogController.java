package com.task.springshop.controller;

import com.task.springshop.formal.repository.OrderProductRepository;
import com.task.springshop.formal.repository.ProductsRepository;
import com.task.springshop.model.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class CatalogController {
    private final ProductsRepository temporaryProducts = ProductsRepository.getInstance();
    private final OrderProductRepository productsInBasket = OrderProductRepository.getInstance();

    @GetMapping(value = {"/", "/index", "/catalog"})
    public String mainFeed(@RequestParam Optional<List<Product>> product, Model model) {
        List<Product> allProducts = product.orElse(temporaryProducts.getAll());
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "product-catalog";
    }

    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam long productId, Model model) {
        int quantity = productsInBasket.get(productId);
        productsInBasket.replace(productId, quantity + 1);
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "product-catalog";
    }

    @PostMapping("/removeFromBasket")
    public String removeFromBasket(@RequestParam long productId, Model model) {
        int quantity = productsInBasket.get(productId);
        if (quantity > 0)
        {
            productsInBasket.replace(productId, quantity - 1);
        }
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "product-catalog";
    }
}
