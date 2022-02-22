package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.other.BeanB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * This bean implements BeanFactoryPostProcessor.
 * For additional help for BeanB,
 * change the initMethod parameter(initMethodB())
 * to the value of a different initMethodOther() method.
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(BeanB.class);
        bd.setInitMethodName("initMethodOther");

        ((DefaultListableBeanFactory) configurableListableBeanFactory)
                .registerBeanDefinition("BeanB", bd);
    }
}
