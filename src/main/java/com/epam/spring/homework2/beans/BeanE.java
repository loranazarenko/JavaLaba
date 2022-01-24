package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * POJO class BeanE with @Component annotation
 * has methods annotated with @PostConstruct and @PreDestroy
 */
@Component
public class BeanE implements MyValidator {

    private String name;
    private int value;

    public BeanE() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Inside postConstruct()");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Inside preDestroy()");
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanE - This is not valid bean");
        }
    }
}
