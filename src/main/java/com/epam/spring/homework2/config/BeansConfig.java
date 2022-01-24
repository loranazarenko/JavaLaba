package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.*;

/**
 * Configuration file with import OtherConfig
 * and Lazy annotation
 */
@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@Import(OtherConfig.class)
public class BeansConfig {

    /**
     * Lazy annotation for the bean BeanF
     */
    @Bean
    @Lazy
    public BeanF getBeanF() {
        return new BeanF();
    }

}
