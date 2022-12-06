package ru.serov.distask.dao.controller.model.product;

import lombok.Data;
import reactor.core.publisher.Flux;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Float implement_cost;
    private Flux<Long> article_ids;
}
