package com.epam.spring.homework1.other;

import com.epam.spring.homework1.beans.BeanC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * POJO class OtherBeanC with @Component annotation injecting BeanC
 */
@Component
public class OtherBeanC {
    @Autowired
    private BeanC beanC;

    // It is null because it has not been initialized
    public OtherBeanC() {
        System.out.println(beanC);
    }

}
