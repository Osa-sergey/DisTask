package ru.serov.distask.dao.controller.model.productentity;

import lombok.Data;

@Data
public class ProductEntityDTO {
    private Long id;
    private String name;
    private String description;
    private Float implement_cost;
}
