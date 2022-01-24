package com.epam.spring.homework2.other;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * POJO class BeanB with @Component annotation,
 * create in order after BeanD
 */
@Component("beanB")
@DependsOn("beanD")
public class BeanB implements MyValidator {

    private String name;
    private int value;

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public BeanB() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private void initMethodB() {
        System.out.println("Inside initMethodB()");
    }

    private void initMethodOther() {
        System.out.println("Inside initMethodOther()");
    }

    private void destroyMethodB() {
        System.out.println("Inside destroyMethodB()");
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanB - This is not valid bean");
        }
    }
}
