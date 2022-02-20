package com.epam.onlinestore.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
* Annotation for a validation
*/
@Documented
@Constraint(validatedBy = DescriptionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDescription {
    String message() default "Invalid product description";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
