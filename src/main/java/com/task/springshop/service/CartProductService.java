package com.task.springshop.service;

import java.math.BigDecimal;
import java.util.Map;

public interface CartProductService {
    BigDecimal calculateOrderPrice(Map<Long, Integer> productsInCart, boolean isDelirevy);
}
