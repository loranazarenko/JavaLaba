package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BeansConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class causes beans creation
 */
public class App {

    public static void main(String[] args) {
        // Retrieving BeansConfig configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        //Calling all beans by name
        for (String bean : context.getBeanDefinitionNames()) {
            System.out.println(bean);
        }

        context.close();
    }
}
