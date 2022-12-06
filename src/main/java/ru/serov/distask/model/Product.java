package ru.serov.distask.model;

import lombok.Data;
import reactor.core.publisher.Flux;
import ru.serov.distask.dao.repository.enity.ArticleEntity;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Float implementCost;
    private Flux<ArticleEntity> articles;
}
