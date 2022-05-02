package com.task.springshop.validator;

import com.task.springshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class ProductQuantityValidator implements ConstraintValidator<ProductQuantity, Map<Long, Integer>> {
    private final ProductService productService;

    @Override
    public boolean isValid(Map<Long, Integer> inCart, ConstraintValidatorContext constraintValidatorContext) {
        log.debug("Product quantity validation");
        Set<Long> keys = inCart.keySet();
        for (Long key : keys) {
            Optional<Integer> productQuantity = productService.findQuantityByProductId(key);
            if (productQuantity.isPresent() && inCart.get(key) > productQuantity.get()) {
                return false;
            }
        }
        return true;
    }
}
