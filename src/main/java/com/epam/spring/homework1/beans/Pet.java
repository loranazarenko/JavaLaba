package com.epam.spring.homework1.beans;

import com.epam.spring.homework1.pet.Animal;
import com.epam.spring.homework1.pet.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
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
