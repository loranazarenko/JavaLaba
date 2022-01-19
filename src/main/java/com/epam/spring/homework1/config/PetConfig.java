package com.epam.spring.homework1.config;

import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.Spider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.homework1.pet"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Spider.class)})
public class PetConfig {

    @Primary
    @Bean
    public Cheetah getBean2() {
        return new Cheetah();
    }

    @Qualifier("myCheetah")
    @Bean("myCheetah")
    public Cheetah getBean1() {
        return new Cheetah();
    }

}
