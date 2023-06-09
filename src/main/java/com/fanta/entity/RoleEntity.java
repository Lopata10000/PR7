package com.fanta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceEntity implements Entity{
    private int serviceId;
    private String name;
    private String description;
    private double price;
}
