package ru.serov.distask.dao.controller.model.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Float implement_cost;
    private List<Long> article_ids;
}
