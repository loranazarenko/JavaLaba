package com.epam.spring.homework1.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanA {
    private String name;

    public BeanA() {
        System.out.println((this.getClass().getSimpleName()));
    }
}
