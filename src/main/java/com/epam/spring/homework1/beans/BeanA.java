package com.epam.spring.homework1.beans;

import org.springframework.stereotype.Component;

/**
 * POJO class BeanA with @Component annotation
 */
@Component
public class BeanA {

    // Constructor without arguments
    public BeanA() {

        System.out.println((this.getClass().getSimpleName()));
    }
}
