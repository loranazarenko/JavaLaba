package com.epam.spring.homework1.pet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Cheetah implements Animal {

    public String getPet() {
        return "Cheetah";
    }
}
