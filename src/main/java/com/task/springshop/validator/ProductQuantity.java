package com.task.springshop.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductQuantityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductQuantity {
    String message() default "Please order available count of product!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
