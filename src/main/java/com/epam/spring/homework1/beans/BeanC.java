package com.epam.spring.homework1.beans;

import org.springframework.stereotype.Component;

/**
 * POJO class BeanC with @Component annotation
 */
@Component
public class BeanC {

    // Constructor without arguments
    public BeanC() {
        System.out.println((this.getClass().getSimpleName()));
    }
}
