package ru.serov.distask.model;

import lombok.Data;
import ru.serov.distask.dao.repository.enity.ArticleEntity;

import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Float implementCost;
    private List<ArticleEntity> articles;
}
