package com.epam.spring.homework1;

import com.epam.spring.homework1.beans.Pet;
import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.pet.Cheetah;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        context.getBean(Pet.class).printPets();

        Cheetah cheetahClass = context.getBean(Cheetah.class);
        System.out.println(cheetahClass);

        Cheetah cheetahName = context.getBean("myCheetah", Cheetah.class);
        System.out.println(cheetahName);

    }

}
