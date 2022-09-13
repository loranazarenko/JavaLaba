package com.epam.onlinestore.exception.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
* Class for a validation the field description
*/
public class DescriptionValidator implements ConstraintValidator<ValidDescription, String> {

    @Override
    public void initialize(ValidDescription contactNumber) {
    }

    @Override
    public boolean isValid(String productDescription, ConstraintValidatorContext cxt) {
        return productDescription.split(" ").length > 1;
    }

}