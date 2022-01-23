package com.epam.spring.homework1.pet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Simple bean Dog
 */
@Component
 @Order(1)
public class Dog implements Animal {

    public String getPet() {
        return "Dog";
    }
}
