package com.epam.spring.homework2.other;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * POJO class BeaC with @Component annotation,
 * create in order after BeanB
 */
@Component
@DependsOn("beanB")
public class BeanC implements MyValidator {

    private String name;
    private int value;

    public BeanC(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public BeanC() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private void initMethodC() {
        System.out.println("Inside initMethodC()");
    }

    private void destroyMethodC() {
        System.out.println("Inside destroyMethodC()");
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanC - This is not valid bean");
        }
    }
}
