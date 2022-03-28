package com.task.springshop.controller;

import com.task.springshop.formal.repository.OrderProductRepository;
import com.task.springshop.formal.repository.OrdersRepository;
import com.task.springshop.formal.repository.ProductsRepository;
import com.task.springshop.model.entity.Order;
import com.task.springshop.model.entity.OrderStatus;
import com.task.springshop.model.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BasketController {
    private final ProductsRepository temporaryProducts = ProductsRepository.getInstance();
    private final OrderProductRepository productsInBasket = OrderProductRepository.getInstance();
    private final OrdersRepository orders = OrdersRepository.getInstance();

    @GetMapping("/basketProducts")
    public String basketProducts(Model model) {
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "basket";
    }

    @PostMapping("/addInBasket")
    public String addInBasket(@RequestParam long productId, Model model) {
        int quantity = productsInBasket.get(productId);
        productsInBasket.replace(productId, quantity + 1);
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "basket";
    }

    @PostMapping("/removeInBasket")
    public String removeInBasket(@RequestParam long productId, Model model) {
        int quantity = productsInBasket.get(productId);
        if (quantity > 0)
        {
            productsInBasket.replace(productId, quantity - 1);
        }
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        return "basket";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@RequestParam String name, @RequestParam String surname, @RequestParam(required = false) String address, @RequestParam String receivingWay, Model model) {
        BigDecimal price = calculateOrderPrice(temporaryProducts, productsInBasket, receivingWay);
        Order newOrder;
        if (!receivingWay.equals("Доставка (5.00 руб.)")) {
            newOrder = new Order(orders.size() + 1, LocalDate.now(), price, "самовывоз", OrderStatus.IN_PROCESS, name, surname);
        }
        else {
            newOrder = new Order(orders.size() + 1, LocalDate.now(), price, address, OrderStatus.IN_PROCESS, name, surname);
        }
        orders.add(newOrder);
        productsInBasket.clearBasket();
        model.addAttribute("allProducts", temporaryProducts.getAll());
        model.addAttribute("productsInBasket", productsInBasket.getAll());
        model.addAttribute("orderMessage", "Your order is in process!");
        return "basket";
    }

    private BigDecimal calculateOrderPrice(ProductsRepository products, OrderProductRepository productsInBasket, String receivingWay) {
        final BigDecimal[] result = {BigDecimal.ZERO};
        List<Product> productList = products.getAll();
        productList.forEach(product -> {
            int quantity = productsInBasket.get(product.getProductId());
            if(quantity != 0) {
                result[0] = result[0].add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        });
        if (receivingWay.equals("Доставка (5.00 руб.)")) {
            result[0] = result[0].add(BigDecimal.valueOf(5));
        }
        return result[0];
    }
}
