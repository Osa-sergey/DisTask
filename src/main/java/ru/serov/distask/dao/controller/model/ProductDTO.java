package ru.serov.distask.dao.controller.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Float implement_cost;
}
