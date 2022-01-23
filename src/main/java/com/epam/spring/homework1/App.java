package com.epam.spring.homework1;

import com.epam.spring.homework1.beans.Pet;
import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.pet.Cheetah;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class causes beans creation
 */
public class App {

    public static void main(String[] args) {

        // Retrieving bean Pet with his method printPets()
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        context.getBean(Pet.class).printPets();

        // Retrieving bean Cheetah by type Task 10
        Cheetah cheetahClass = context.getBean(Cheetah.class);
        System.out.println(cheetahClass);

        // Retrieving bean Cheetah by name Task 10
        Cheetah cheetahName = context.getBean("myCheetah", Cheetah.class);
        System.out.println(cheetahName);
        // Have a different place in memory
    }

}
