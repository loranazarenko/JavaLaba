package com.epam.spring.homework2.other;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.stereotype.Component;

/**
 * POJO class BeaD with @Component annotation
 * creating first
 */
@Component("beanD")
public class BeanD implements MyValidator {

    private String name;
    private int value;

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public BeanD() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private void initMethodD() {
        System.out.println("Inside initMethodD()");
    }

    private void destroyMethodD() {
        System.out.println("Inside destroyMethodD()");
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanD - This is not valid bean");
        }
    }
}
