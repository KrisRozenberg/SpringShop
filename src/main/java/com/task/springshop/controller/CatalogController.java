package com.task.springshop.controller;

import com.task.springshop.service.CartProductService;
import com.task.springshop.service.ProductService;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes("productsInCart")
public class CatalogController {
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
    private final ProductService productService;
    private final CartProductService cartProductService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("allProducts", productService.findAll());
        if (model.getAttribute("productsInCart") == null) {
            model.addAttribute("productsInCart", new CartProducts());
        }
    }

    @GetMapping(value = {"/", "/index", "/catalog"})
    public String mainFeed() {
        return "product-catalog";
    }

    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute("productsInCart") CartProducts productsInCart, @RequestParam Long productId) {
        cartProductService.addProduct(productsInCart, productId);

        return "product-catalog";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@ModelAttribute("productsInCart") CartProducts productsInCart, @RequestParam Long productId) {
        cartProductService.removeProduct(productsInCart, productId);

        return "product-catalog";
    }
}
