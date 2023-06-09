package com.fanta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEntity implements Entity {
    private int doctorId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String speciality;
}
