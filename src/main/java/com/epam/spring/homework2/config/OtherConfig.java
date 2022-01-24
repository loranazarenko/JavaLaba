package com.epam.spring.homework2.config;

import com.epam.spring.homework2.other.BeanB;
import com.epam.spring.homework2.other.BeanC;
import com.epam.spring.homework2.other.BeanD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * The configuration file that specifies the fields  for the beans from
 * the application.properties file and specifies the name of the custom
 * methods for the init Method and destroy Method attributes
 */
@Configuration
@ComponentScan("com.epam.spring.homework2.other")
@PropertySource("classpath:application.properties")
public class OtherConfig {

    /**
     * Getting bean field values from application.properties file
     */
    @Value("${BeanD.name}")
    private String nameD;
    @Value("${BeanD.value}")
    private int valueD;

    /**
     * Specify custom method names for init Method and destroy Method attributes
     */
    @Bean(initMethod = "initMethodD", destroyMethod = "destroyMethodD")
    public BeanD getBeanD() {
        return new BeanD(nameD, valueD);
    }

    @Value("${BeanB.name}")
    private String nameB;
    @Value("${BeanB.value}")
    private int valueB;

    @Bean(initMethod = "initMethodB", destroyMethod = "destroyMethodB")
    @DependsOn("beanD")
    public BeanB getBeanB() {
        return new BeanB(nameB, valueB);
    }

    @Value("${BeanC.name}")
    private String nameC;
    @Value("${BeanC.value}")
    private int valueC;

    @Bean(initMethod = "initMethodC", destroyMethod = "destroyMethodC")
    @DependsOn("beanB")
    public BeanC getBeanC() {
        return new BeanC(nameC, valueC);
    }

}
