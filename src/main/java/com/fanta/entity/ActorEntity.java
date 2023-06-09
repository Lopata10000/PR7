package com.fanta.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorEntity implements Entity {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
