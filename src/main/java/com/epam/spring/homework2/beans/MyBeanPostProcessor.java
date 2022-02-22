package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.interfaces.MyValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * With the help of this bean,
 * the validation of all beans will be carried out
 * after initialize
 * All name fields must contain values (not null),
 * and value fields must contain only positive values.
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    private String name;
    private int value;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyValidator) {
            ((MyValidator) bean).validate();
        }
        return bean;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
