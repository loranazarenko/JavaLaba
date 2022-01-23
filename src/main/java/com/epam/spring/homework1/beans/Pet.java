package com.epam.spring.homework1.beans;

import com.epam.spring.homework1.pet.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Bean Pet, which will inject a collection for created animals in a certain order
 */
@Component
public class Pet {

    @Autowired
    public List<Animal> animals;

    public void printPets() {
        for (Animal animal : animals) {
            System.out.println(animal.getPet());
        }
    }
}
