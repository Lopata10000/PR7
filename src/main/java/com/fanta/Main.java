package com.fanta;


import com.fanta.entity.ActorEntity;
import com.fanta.services.ActorService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        ActorEntity newActor = ActorEntity.builder()
                .firstName("Fanta")
                .lastName("Petro")
                .birthDate(LocalDate.of(1990, 5, 15))
                .build();

        ActorService.getInstance().addNewActor(newActor);
        System.out.println(newActor);
        ActorEntity selected = ActorService.getInstance().getActorById(2);
        System.out.println(selected);
    }
}