package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * POJO class BeanA with @Component annotation,
 * implements the InitializingBean and DisposableBean interfaces
 */
@Component
public class BeanA implements InitializingBean, DisposableBean, MyValidator {

    private String name;
    private int value;

    public BeanA() {
        System.out.println((this.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void destroy() {
        System.out.println("Inside DisposableBean.destroy()");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Inside InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("BeanA - This is not valid bean");
        }
    }
}
