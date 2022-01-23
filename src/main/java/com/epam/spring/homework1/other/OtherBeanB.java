package com.epam.spring.homework1.other;

import com.epam.spring.homework1.beans.BeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * POJO class OtherBeanB with @Component annotation injecting BeanB
 */
@Component
public class OtherBeanB {
    private BeanB beanB;

    @Autowired
    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
        System.out.println(this.getClass().getSimpleName() + ".  " + beanB.getClass().getSimpleName()
                + " was injected through the setter ");
    }

}
