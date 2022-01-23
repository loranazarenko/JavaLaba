package com.epam.spring.homework1.beans;

import org.springframework.stereotype.Component;

/**
 * POJO class BeanB with @Component annotation
 */
@Component
public class BeanB {

    // Constructor without arguments
    public BeanB() {
        System.out.println((this.getClass().getSimpleName()));
    }
}
