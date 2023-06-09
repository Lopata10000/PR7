package main.java.org.krupanych;


import org.krupanych.entity.AppointmentEntity;
import org.krupanych.services.AppointmentService;

public class Main {

    public static void main(String[] args) {

        AppointmentEntity appointmentEntity = AppointmentService.getInstance().getAppointmentById(1);
        System.out.println(appointmentEntity);

    }
}