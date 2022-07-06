package com.task.springshop.util;

import com.task.springshop.validator.ProductQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignedInOrderInfo {
    @ProductQuantity
    private Map<Long, Integer> productQuantity;

    @Size(max = 25, message = "Login must contain less then 30 characters.")
    private String login;

    @Size(max = 100, message = "Address must contain less then 100 characters.")
    private String address;

    private boolean isDelivery;
}
