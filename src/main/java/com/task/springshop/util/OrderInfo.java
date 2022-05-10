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
public class OrderInfo {
    @ProductQuantity
    private Map<Long, Integer> productQuantity;

    @Size(max = 30, message = "Name must contain less then 30 characters.")
    private String name;

    @Size(max = 30, message = "Surname must contain less then 30 characters.")
    private String surname;

    @Size(max = 100, message = "Address must contain less then 100 characters.")
    private String address;

    private boolean isDelivery;
}
