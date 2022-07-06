package com.task.springshop.controller;

import com.task.springshop.entity.Order;
import com.task.springshop.entity.OrderStatus;
import com.task.springshop.entity.User;
import com.task.springshop.exception.ApplicationException;
import com.task.springshop.exception.ValidationException;
import com.task.springshop.service.CartProductService;
import com.task.springshop.service.OrderService;
import com.task.springshop.service.UserService;
import com.task.springshop.util.SignedInOrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {
    private final CartProductService cartProductService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/getOrderList")
    public ResponseEntity<List<Order>> getOrderList(@RequestParam("login") String login) {
        Optional<User> user = userService.findUserByLogin(login);
        List<Order> orderList = new ArrayList<>();
        if (user.isPresent()) {
            orderList = orderService.findClientOrders(user.get());
        }

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/rejectOrder")
    public  ResponseEntity<?> rejectOrder(@RequestParam("id") long id) {
        orderService.updateStatusById(id, OrderStatus.REJECTED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/makeSignedInOrder")
    public ResponseEntity<?> makeSignedInOrder(@Valid @RequestBody SignedInOrderInfo signedInOrderInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            List<String> errors = new ArrayList<>();

            bindingResult
                    .getFieldErrors()
                    .forEach(fieldError -> {
                        errors.add(fieldError.getDefaultMessage());
                    });
            throw new ValidationException(errors);
        }

        BigDecimal price = cartProductService.calculateOrderPrice(signedInOrderInfo.getProductQuantity(), signedInOrderInfo.isDelivery());

        Optional<User> optionalUser = userService.findUserByLogin(signedInOrderInfo.getLogin());
        if (optionalUser.isEmpty()) {
            throw new ApplicationException("Server consistency error");
        }
        User user = optionalUser.get();

        orderService.addSignedInOrder(signedInOrderInfo, user, price);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
