package com.task.springshop.controller;

import com.task.springshop.entity.Order;
import com.task.springshop.service.CartProductService;
import com.task.springshop.service.OrderService;
import com.task.springshop.service.ProductService;
import com.task.springshop.util.CartProducts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@SessionAttributes("productsInCart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final ProductService productService;
    private final CartProductService cartProductService;
    private final OrderService orderService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("allProducts", productService.findAll());
        model.addAttribute("order", new Order());
    }

    @GetMapping("/cartProducts")
    public String cartProducts() {
        return "cart";
    }

    @PostMapping("/addInCart")
    public String addInCart(@ModelAttribute("productsInCart") CartProducts productsInCart, @RequestParam long productId) {
        cartProductService.addProduct(productsInCart, productId);
        return "cart";
    }

    @PostMapping("/removeInCart")
    public String removeInCart(@ModelAttribute("productsInCart") CartProducts productsInCart, @RequestParam long productId) {
        cartProductService.removeProduct(productsInCart, productId);
        return "cart";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@ModelAttribute("productsInCart") @Valid CartProducts productsInCart, BindingResult bindingResult1,
                            @ModelAttribute("order") @Valid Order order, BindingResult bindingResult2,
                            @RequestParam String receivingWay, Model model) {
        if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {
            logger.debug("Fields errors 1 and 2: {}, {}", bindingResult1.getErrorCount(), bindingResult2.getErrorCount());
            return "cart";
        }
        BigDecimal price = cartProductService.calculateOrderPrice(productsInCart, receivingWay);
        orderService.addUnsignedOrder(productsInCart, price, receivingWay, order.getName(), order.getSurname(),
                order.getAddress());
        productsInCart.clear();
        model.addAttribute("orderMessage", "Your order is in process!");
        return "cart";
    }
}
