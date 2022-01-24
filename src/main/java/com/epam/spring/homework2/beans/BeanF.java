package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.MyValidator;

/**
 * POJO class BeanF with @Component annotation
 */
public class BeanF implements MyValidator {

    private String name;
    private int value;

    public BeanF() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanF - This is not valid bean");
        }
    }
}
