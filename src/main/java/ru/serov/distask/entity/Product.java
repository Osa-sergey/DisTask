package ru.serov.distask.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Float implementCost;
}
