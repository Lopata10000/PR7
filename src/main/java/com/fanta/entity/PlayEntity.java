package com.fanta.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentEntity implements Entity{
    private int appointmentId;
    private LocalDateTime appointmentDate;
    private PatientEntity patientEntity;
    private DoctorEntity doctorEntity;
    private ServiceEntity serviceEntity;
}
